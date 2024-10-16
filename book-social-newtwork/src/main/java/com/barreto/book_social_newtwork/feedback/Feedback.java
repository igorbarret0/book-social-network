package com.barreto.book_social_newtwork.feedback;

import com.barreto.book_social_newtwork.book.Book;
import com.barreto.book_social_newtwork.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tb_feedbacks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Feedback extends BaseEntity {

    private Double note; // 1 - 5 stars

    private String comment;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
