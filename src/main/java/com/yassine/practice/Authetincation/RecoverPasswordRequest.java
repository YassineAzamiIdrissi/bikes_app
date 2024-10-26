package com.yassine.practice.Authetincation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RecoverPasswordRequest {
    @NotNull(message = "recovery password has to be specified")
    @NotEmpty(message = "recovery password mustn't be empty")
    @NotBlank(message = "recovery password mustn't be blank")
    @Size(min = 8, message = "a password must have a minimum of 8")
    private String password;
    private String confirmPassword;
}
