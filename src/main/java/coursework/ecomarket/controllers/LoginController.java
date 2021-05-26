package coursework.ecomarket.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import coursework.ecomarket.entities.Client;
import coursework.ecomarket.services.UserDetailServiceImpl;

@Controller
@Transactional
public class LoginController {
    @Autowired
    UserDetailServiceImpl uService;
    
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
}
