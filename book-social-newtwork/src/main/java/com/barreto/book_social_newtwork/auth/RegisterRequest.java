package com.barreto.book_social_newtwork.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

        @NotBlank(message = "Firstname is mandatory")
        String firstName,

        @NotBlank(message = "Lastname is mandatory")
        String lastName,

        @NotBlank(message = "Email is mandatory")
        @Email(message = "Please, type a valid email")
        String email,

        @NotBlank(message = "Password is mandatory")
        @Size(min = 8, message = "Password should contain at least 8 characters")
        String password
) {
}
