package coursework.ecomarket.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {
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
    public String Catalog() {
        return "html/catalog";
    }
    @GetMapping("/userpage")
    public String UserPage() {
        return "html/userPage";
    }
    @GetMapping("/order")
    public String OrderPage() {
        return "html/orderPage.html";
    }
    @GetMapping("/cart")
    public String CartPage() {
        return "html/cartPage.html";
    }
    @GetMapping("/product")
    public String ProductPage() {
        return "html/productPage.html";
    }
    @GetMapping("/registration") 
    public String Registration() {
        return "html/registrationForm.html";
    }
    @GetMapping("/login")
    public String Login() {
        return "html/loginForm.html";
    }
}