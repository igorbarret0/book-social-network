package com.barreto.book_social_newtwork.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookRequest(

        Long id,

        @NotBlank(message = "Title is mandatory")
        String title,

        @NotBlank(message = "isbn is mandatory")
        String authorName,

        @NotBlank(message = "ISBN is mandatory")
        String isbn,

        @NotBlank(message = "Synopsis is mandatory")
        String synopsis,

        @NotNull
        boolean archived,

        @NotNull
        boolean shareable

) {
}
