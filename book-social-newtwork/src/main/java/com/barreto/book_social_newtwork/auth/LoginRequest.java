package com.barreto.book_social_newtwork.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(

        @Email(message = "Email should be in a format valid")
        @NotBlank(message = "Email is  mandatory")
        String email,

        @NotBlank(message = "Password is mandatory")
        @Size(min = 8, message = "Password should contain at least 8 characters")
        String password
) {
}
