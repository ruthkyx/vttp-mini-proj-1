package miniproj.vttp23.iss.miniproj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import miniproj.vttp23.iss.miniproj.model.User;

@Controller
@RequestMapping
public class UserController {
    
    // @RequestMapping(value="/newUser", method= RequestMethod.POST)
    @PostMapping("/newUser")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);

        return "signUp";
    }

    // GET user data, if dont exist, ask them to sign up
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
}
