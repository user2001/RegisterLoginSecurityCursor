package com.example.registerloginsecuritycursor.registration;


import lombok.*;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;

}