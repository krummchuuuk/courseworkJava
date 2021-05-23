package coursework.ecomarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import coursework.ecomarket.services.ProductsService;

@Controller
public class MapController {
    @Autowired
    ProductsService pService;

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
    public String ProductPage(Model model, @RequestParam(name="id", required = false) int id) {
        model.addAttribute("product", pService.findById(id));
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