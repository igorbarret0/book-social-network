package com.barreto.book_social_newtwork.book;

public record BookResponse(

        Long id,
        String title,
        String authorName,
        String isbn,
        String synopsis,
        String ownerName,
        byte[] cover,
        double rate,
        boolean archived,
        boolean shareable
) {
}
