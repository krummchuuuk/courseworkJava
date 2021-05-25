package coursework.ecomarket.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import coursework.ecomarket.entities.Products;
import coursework.ecomarket.services.CartService;
import coursework.ecomarket.services.ProductsService;
import coursework.ecomarket.services.UserDetailServiceImpl;

@Controller
public class MapController {
    @Autowired
    private ProductsService pService;
    @Autowired
    private UserDetailServiceImpl uService;
    @Autowired
    private CartService cService;
    
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
    public String Login() {
        return "html/loginForm.html";
    }
    @GetMapping("/addProduct")
    public String addProduct(@RequestParam("id") String id) {
        Carts cart = ((Client)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getCart();
        Products product = pService.findById(Integer.valueOf(id));
        cart.setCost(cart.getCost()+product.getPrice());
        cart.addProduct(cart, product);
        cService.update(cart);
        return ("redirect:product?id="+id);
    }
    @GetMapping("/removeProduct")
    public String deleteProduct(@RequestParam("id") String id) {
        Carts cart = ((Client)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getCart();
        cart.getProducts().remove(pService.findById(Integer.valueOf(id)));
        return ("redirect:cart");
    }
}