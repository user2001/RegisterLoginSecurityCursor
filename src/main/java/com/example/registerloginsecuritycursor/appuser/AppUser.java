package com.example.registerloginsecuritycursor.appuser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "app_users")
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    @NotEmpty(message = "First name cannot be empty.")
    private String firstName;
    @Column(name = "last_name")
    @NotEmpty(message = "Last name cannot be empty.")
    private String lastName;
    @Column(name = "username")
    @NotEmpty(message = "Username cannot be empty.")
    private String username;
    @Column(name = "email")
    @NotEmpty(message = "User's email cannot be empty.")
    @Email(message = "Please provide a valid email id")
    private String email;
    @Column(name = "password")
    @NotEmpty(message = "Password can not be empty")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private AppUserRole appUserRole;

    @Column(name = "enabled")
    private boolean enabled = true;

    public AppUser(String firstName,
                   String lastName,
                   String username,
                   String email,
                   String password,
                   AppUserRole appUserRole
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
