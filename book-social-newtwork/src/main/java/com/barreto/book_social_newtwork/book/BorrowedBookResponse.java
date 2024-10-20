package com.barreto.book_social_newtwork.book;

public record BorrowedBookResponse(

        Long id,
        String title,
        String authorName,
        String isbn,
        double rate,
        boolean returned,
        boolean returnApproved

) {
}
