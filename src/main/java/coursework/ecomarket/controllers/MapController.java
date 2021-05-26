package coursework.ecomarket.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import coursework.ecomarket.entities.CartProduct;
import coursework.ecomarket.entities.Carts;
import coursework.ecomarket.entities.Client;
import coursework.ecomarket.entities.Order;
import coursework.ecomarket.entities.Products;
import coursework.ecomarket.repositories.OrderRepo;
import coursework.ecomarket.services.CartService;
import coursework.ecomarket.services.ProductsService;
import coursework.ecomarket.services.UserDetailServiceImpl;

@Controller
@Transactional
public class MapController {
    @Autowired
    private ProductsService pService;
    @Autowired
    private UserDetailServiceImpl uService;
    @Autowired
    private CartService cService;
    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Autowired
    OrderRepo oRepo;

    @GetMapping("/")
    public String HomePage() {
        return "html/index";
    }
    @GetMapping("/about")
    public String AboutPage() {
        return "html/aboutus";
    }
    @GetMapping("/delivery")
    public String Delivery() {
        return "html/delivery";
    }
    @GetMapping("/catalog") 
    public String Catalog(Model model, @RequestParam(name = "category", required = false) String category) {
        model.addAttribute("products", pService.showByCategory(category));
        return "html/catalog";
    }
    @GetMapping("/userpage")
    public String UserPage(Model model) {
        model.addAttribute("client", (Client)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "html/userPage";
    }
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
    @GetMapping("/product")
    public String ProductPage(Model model, @RequestParam(name="id", required = false) int id) {
        model.addAttribute("product", pService.findById(id));
        return "html/productPage.html";
    }
    @GetMapping("/registration") 
    public String Registration() {
        return "html/registrationForm.html";
    }
    @PostMapping("/registration")
    public String newClient(Model model, @RequestParam("name") String name, @RequestParam("surname") String surname, @RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("password") String password) {
        uService.saveClient(new Client(name, surname, email, username, password));
        return "redirect:/";
    }
    @GetMapping("/login")
    public String Login(Model model) {
        return "html/loginForm.html";
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
        oRepo.save(order);
        return ("redirect:cart");
    }
}