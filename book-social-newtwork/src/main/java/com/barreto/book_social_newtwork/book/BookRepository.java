package com.barreto.book_social_newtwork.book;

import com.barreto.book_social_newtwork.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("""
           SELECT book
           FROM Book book
           WHERE book.archived = false
           AND
           book.shareable = true
           AND
           book.owner.id != :userId
           """)
    Page<Book> findAllDisplayableBooks(Pageable pageable,  Long userId);

    Page<Book> findAllBooksByOwner(Pageable pageable, User user);

}
