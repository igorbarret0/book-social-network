package com.barreto.book_social_newtwork.feedback;

import com.barreto.book_social_newtwork.book.Book;
import org.springframework.stereotype.Service;


@Service
public class FeedbackMapper {

    public Feedback toFeedback(FeedbackRequest request, Book book) {

        var feedback = new Feedback();
        feedback.setNote(request.note());
        feedback.setComment(request.comment());
        feedback.setBook(book);

        return feedback;
    }

    public FeedbackResponse toFeedbackResponse(Feedback f, Long userId) {

        boolean ownFeedback = f.getCreatedBy().equals(userId);

        var feedbackResponse = new FeedbackResponse(
                f.getNote(),
                f.getComment(),
                ownFeedback
        );

        return feedbackResponse;
    }
}
