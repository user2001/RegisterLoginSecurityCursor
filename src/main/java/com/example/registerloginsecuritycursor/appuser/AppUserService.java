package com.example.registerloginsecuritycursor.appuser;


import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    private final AppUserRepo appUserRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format(USER_NOT_FOUND_MSG, username)
        ));
    }

    public void singUpUser(AppUser appUser) {
        boolean userExist = appUserRepo.findByUsername(appUser.getUsername()).isPresent();
        if (userExist) {
            throw new IllegalStateException("email already exist");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepo.save(appUser);
    }

}
