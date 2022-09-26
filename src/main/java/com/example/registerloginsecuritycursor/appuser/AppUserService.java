package com.example.registerloginsecuritycursor.appuser;


import com.example.registerloginsecuritycursor.exception.UserAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService, AppUserServiceInt {
    private final static String USER_NOT_FOUND_MSG =
            "user with username %s not found";
    private final AppUserRepo appUserRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format(USER_NOT_FOUND_MSG, username)
        ));
    }

    public void singUpUser(AppUser appUser) throws UserAlreadyExistException {
        if(checkIfUserExist(appUser.getUsername())){
            throw new UserAlreadyExistException("User with this username already exist");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUser.setAppUserRole(AppUserRole.USER);
        appUser.setEnabled(true);
        appUserRepo.save(appUser);
    }

    @Override
    public List<AppUser> findAll() {
        return appUserRepo.findAllByOrderByLastNameAsc();
    }

    @Override
    public AppUser findById(Long theId) {
        Optional<AppUser> result = appUserRepo.findById(theId);

        AppUser user = null;

        if (result.isPresent()) {
            user = result.get();
        } else {
            // we didn't find the user
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return user;
    }

    @Override
    public void save(AppUser user) {
        appUserRepo.save(user);
    }

    @Override
    public void deleteById(Long theId) {
        appUserRepo.deleteById(theId);
    }

    public boolean checkIfUserExist(String username) {
        return appUserRepo.findByUsername(username).isPresent();
    }
}
