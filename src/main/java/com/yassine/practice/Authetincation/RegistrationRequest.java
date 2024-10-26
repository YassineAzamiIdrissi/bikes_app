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
public class RegistrationRequest {
    @NotNull(message = "firstname has to be specified !")
    @NotBlank(message = "firstname mustn't be blank !")
    @NotEmpty(message = "firstname mustn't be empty !")
    private String firstname;
    @NotNull(message = "lastname has to be specified !")
    @NotBlank(message = "lastname mustn't be blank !")
    @NotEmpty(message = "lastname mustn't be empty !")
    private String lastname;
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
