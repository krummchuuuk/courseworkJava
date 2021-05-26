package coursework.ecomarket.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import coursework.ecomarket.entities.Products;
import coursework.ecomarket.services.OrderService;

@Controller
public class OrderController {
    @Autowired
    OrderService oService;

    @GetMapping("/orderpage")
    public String showOrder(Model model, @RequestParam(name = "id", required = false) int id) {
        model.addAttribute("order", oService.getOrderById(id));
        int cost = 0;
        Set<Products> prod = oService.getOrderById(id).getProd();
        for (Products i: prod) {
            cost += i.getPrice();
        }
        model.addAttribute("cost", cost);
        return "html/orderPage";
    }
}
