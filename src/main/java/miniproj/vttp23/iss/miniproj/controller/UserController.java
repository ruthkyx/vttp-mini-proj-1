package miniproj.vttp23.iss.miniproj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import miniproj.vttp23.iss.miniproj.model.User;
import miniproj.vttp23.iss.miniproj.service.AppService;

@Controller
@RequestMapping
public class UserController {

    @Autowired
    private AppService appSvc;

    public String landingPage() {
        return "login";
    }

    // GET user data, if dont exist, ask them to sign up
    @GetMapping("/login")
    public String getLogin(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@Valid @ModelAttribute User user, BindingResult br, HttpSession sess) {
        if(br.hasErrors()) {
            return "login";
        }

        if(!appSvc.hasUser(user.getUsername())) {
            FieldError error = new FieldError("username", "", "Unable to login");
            br.addError(error);
            return "login";
        }

        User existingUser = appSvc.getUser(user.getUsername());
        // add session
        
        return "redirect:/home";
    }
    
    // NEW USER

    @GetMapping("/newUser")
    public String toAdd(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        return "signUp";
    }

    @PostMapping("/newUser")
    public String afterAdd(@Valid @ModelAttribute User newUser, BindingResult br, Model model) {
        if(br.hasErrors()) {
            return "register";
        }

        if(appSvc.hasUser(newUser.getUsername())) {
            FieldError error = new FieldError("user", "username", "user already exists");
            br.addError(error);
            return "signUp";
        }

        appSvc.addUser(newUser);
        model.addAttribute("user", new User());
        return "redirect:/login";
    }

}
