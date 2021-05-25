package coursework.ecomarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import coursework.ecomarket.entities.Carts;
import coursework.ecomarket.entities.Client;
import coursework.ecomarket.services.ProductsService;
import coursework.ecomarket.services.UserDetailServiceImpl;

@Controller
public class MapController {
    @Autowired
    private ProductsService pService;
    @Autowired
    private UserDetailServiceImpl uService;
    
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
        model.addAttribute("cart", cliCart);
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
        cart.setProducts(pService.findById(Integer.valueOf(id)));
        return ("redirect:product?id="+id);
    }
    @GetMapping("/deleteItem")
    public String deleteProduct(@RequestParam("id") String id) {
        Carts cart = ((Client)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getCart();
        cart.getProducts().remove(pService.findById(Integer.valueOf(id)));
        return ("redirect:cart");
    }
}