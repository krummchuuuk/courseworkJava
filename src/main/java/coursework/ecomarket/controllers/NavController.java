package coursework.ecomarket.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {
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
}
