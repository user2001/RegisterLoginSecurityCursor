package com.example.registerloginsecuritycursor.appuser;

import java.util.List;

public interface AppUserServiceInt {
    public List<AppUser> findAll();

    public AppUser findById(Long theId);

    public void save (AppUser theEmployee);

    public void deleteById(Long theId);

}
