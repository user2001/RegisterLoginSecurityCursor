package com.example.registerloginsecuritycursor.appuser;

import com.example.registerloginsecuritycursor.exception.UserAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/users")
public class AppUserController {
    private final AppUserService appUserService;

    @GetMapping("/list")
    public String listOfUsers(Model theModel) {
        List<AppUser> usersList = appUserService.findAll();
        theModel.addAttribute("users", usersList);
        return "list_users";

    }

    @GetMapping("/register")
    public String showFormForRegistration(Model theModel) {
        AppUser appUser = new AppUser();
        theModel.addAttribute("appUser", appUser);
        return "registration";
    }

    @GetMapping("/add")
    public String addUser(Model theModel) {
        AppUser appUser = new AppUser();
        theModel.addAttribute("appUser", appUser);
        return "registration";
    }

    @PostMapping("/save")
    public String saveNewUser(@Valid @ModelAttribute("appUser") AppUser appUser, BindingResult result) {
        if (result.hasErrors()) {
            return "registration";
        }
        try {
            appUserService.singUpUser(appUser);
        } catch (UserAlreadyExistException e) {
            result.rejectValue("username",
                    "appUser.getUsername()", "Username already exist");
            return "registration";
        }
        return "redirect:/users/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("userId") Long theId,
                                    Model theModel) {
        AppUser appUser = appUserService.findById(theId);
        // set employee as a model attribute to pre-populate the form
        theModel.addAttribute("appUser", appUser);
        // send over to our form
        return "registration";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("userId") Long theId) {
        appUserService.deleteById(theId);
        return "redirect:/users/list";
    }
}