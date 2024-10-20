package com.barreto.book_social_newtwork.book;

import com.barreto.book_social_newtwork.history.BookTransactionHistory;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {

    public Book toBook(BookRequest request) {

        var book = new Book();
        book.setTitle(request.title());
        book.setAuthorName(request.authorName());
        book.setIsbn(request.isbn());
        book.setSynopsis(request.synopsis());
        book.setArchived(false);
        book.setShareable(request.shareable());

        return book;
    }

    public BookResponse toBookResponse(Book book) {

        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthorName(),
                book.getIsbn(),
                book.getSynopsis(),
                book.getOwner().getFullName(),
                null,
                book.getRate(),
                book.isArchived(),
                book.isShareable()
        );
    }

    public BorrowedBookResponse toBorrowedBookResponse(BookTransactionHistory bookTransactionHistory) {

        var borrowedBookResponse = new BorrowedBookResponse(
                bookTransactionHistory.getId(),
                bookTransactionHistory.getBook().getTitle(),
                bookTransactionHistory.getBook().getAuthorName(),
                bookTransactionHistory.getBook().getIsbn(),
                bookTransactionHistory.getBook().getRate(),
                bookTransactionHistory.isReturned(),
                bookTransactionHistory.isReturnApproved()
        );

        return borrowedBookResponse;
    }

}
