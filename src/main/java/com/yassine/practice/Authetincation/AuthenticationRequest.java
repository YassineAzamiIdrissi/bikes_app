package com.yassine.practice.Authetincation;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthenticationRequest {
    @NotNull(message = "email has to be specified !")
    @NotBlank(message = "email mustn't be blank !")
    @NotEmpty(message = "email mustn't be empty !")
    @Email(message = "Invalid mail format")
    private String email;
    @NotNull(message = "password has to be specified !")
    @NotBlank(message = "password mustn't be blank !")
    @NotEmpty(message = "password mustn't be empty !")
    @Size(min = 8, message = "a password has to be of a minimum of 8 characters..")
    private String password;
}
