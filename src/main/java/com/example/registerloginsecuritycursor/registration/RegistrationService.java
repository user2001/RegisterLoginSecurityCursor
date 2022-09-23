package com.example.registerloginsecuritycursor.registration;


import com.example.registerloginsecuritycursor.appuser.AppUser;
import com.example.registerloginsecuritycursor.appuser.AppUserRepo;
import com.example.registerloginsecuritycursor.appuser.AppUserRole;
import com.example.registerloginsecuritycursor.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final AppUserService appUserService;
    private final AppUserRepo appUserRepo;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = appUserRepo.findByUsername(request.getUsername()).isPresent();
        if (isValidEmail) {
            throw new IllegalStateException("username already exist, try a different one ");
        }
        appUserService.singUpUser(new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getUsername(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER
        ));
        return "Thanks for registration";
    }
}