package coursework.ecomarket.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import coursework.ecomarket.entities.CartProduct;
import coursework.ecomarket.entities.Carts;
import coursework.ecomarket.entities.Client;
import coursework.ecomarket.entities.Order;
import coursework.ecomarket.entities.Products;
import coursework.ecomarket.services.OrderService;
import coursework.ecomarket.services.ProductsService;

@Controller
@Transactional
public class CartController {
    @Autowired
    ProductsService pService;
    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Autowired
    OrderService oService;

    @GetMapping("/order")
    public String OrderPage() {
        return "html/orderPage.html";
    }
    @GetMapping("/cart")
    public String CartPage(Model model) {
        Client cli = (Client)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Carts cliCart = cli.getCart();
        Set<CartProduct> pSet = cliCart.getProducts();
        List<Products> products = new ArrayList();
        for (CartProduct i: pSet) {
            products.add(i.getProduct());
        }
        model.addAttribute("cart", cliCart);
        model.addAttribute("products", products);
        return "html/cartPage.html";
    }
    @GetMapping("/addProduct")
    public String addProduct(@RequestParam("id") String id) {
        Carts cart = ((Client)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getCart();
        Products product = pService.findById(Integer.valueOf(id));
        cart.setCost(cart.getCost()+product.getPrice());
        cart.addProduct(cart, product);
        EntityManager manager = entityManagerFactory.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(cart);
        manager.getTransaction().commit();
        return ("redirect:product?id="+id);
    }
    @GetMapping("/removeProduct")
    public String deleteProduct(@RequestParam("id") String id) {
        Carts cart = ((Client)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getCart();
        cart.setCost(cart.getCost()-pService.findById(Integer.valueOf(id)).getPrice());
        CartProduct cp = cart.removeProduct(pService.findById(Integer.valueOf(id)));
        EntityManager manager = entityManagerFactory.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(cart);
        manager.getTransaction().commit();
        manager.getTransaction().begin();
        manager.remove(manager.merge(cp));
        manager.getTransaction().commit();
        return ("redirect:cart");
    }

    @GetMapping("/createOrder")
    public String createOrder() {
        Client cli = (Client)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Products> prod = new HashSet<>();
        for (CartProduct i: cli.getCart().getProducts()) {
            prod.add(i.getProduct());
        }
        Order order = new Order(prod,(Client)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        cli.getCart().setCost(0);
        EntityManager manager = entityManagerFactory.createEntityManager();
        manager.getTransaction().begin();
        cli.getOrders().add(order);
        manager.merge(cli);
        manager.getTransaction().commit();
        manager.getTransaction().begin();
        manager.merge(cli.getCart());
        manager.getTransaction().commit();
        Iterator<CartProduct> i = cli.getCart().getProducts().iterator();
        try {
            while (i.hasNext()) {
            CartProduct s = i.next();
            manager.getTransaction().begin();
            manager.remove(manager.merge(cli.getCart().removeProduct(pService.findById(Integer.valueOf(s.getProduct().getId())))));
            manager.getTransaction().commit();
            }
        }
        catch(Exception exc) {
            manager.getTransaction().begin();
            CartProduct last = cli.getCart().getProducts().iterator().next();
            manager.remove(manager.merge(cli.getCart().removeProduct(pService.findById(Integer.valueOf(last.getProduct().getId())))));
            manager.merge(cli.getCart());
            manager.getTransaction().commit();
        }
        oService.saveChanges(order);
        return ("redirect:cart");
    }
}
