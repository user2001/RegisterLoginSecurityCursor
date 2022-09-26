package com.example.registerloginsecuritycursor.controller;

import com.example.registerloginsecuritycursor.appuser.AppUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AppUserRepo appUserRepo;

    @GetMapping("/welcome")
    public String getWelcomePage() {
        return "welcome_page";
    }

    // Login form
    @RequestMapping("/login")
    public String getLoginPage() {
        return "login";
    }


    @GetMapping("/success")
    public String getSuccessPage() {
        return "success";
    }

    @GetMapping("/error")
    public String getErrorPage() {
        return "error_page";
    }


//    @GetMapping("/register")
//    public String getRegisterPage(Model model) {
//        model.addAttribute("user", new AppUser());
//        return "registration";
//    }
//    @PostMapping("/process_register")
//    public String processRegister(RegistrationRequest user) {
//        registrationService.register(user);
//        return "success";
//    }
}
