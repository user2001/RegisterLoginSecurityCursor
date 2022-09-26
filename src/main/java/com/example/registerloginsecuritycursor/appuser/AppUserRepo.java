package com.example.registerloginsecuritycursor.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
@Transactional(readOnly = true)
public interface AppUserRepo extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByUsername(String username);
    public List<AppUser> findAllByOrderByLastNameAsc();

}
