package com.barreto.book_social_newtwork.feedback;

import jakarta.validation.constraints.*;

public record FeedbackRequest(

    @Positive(message = "Note must be positive")
    @Min(value = 0)
    @Max(value = 5)
    Double note,

    @NotBlank(message = "String is mandatory")
    String comment,

    @NotNull(message = "A book ID is mandatory")
    Long bookId
) {
}
