package com.barreto.book_social_newtwork.feedback;

public record FeedbackResponse(

        Double note,
        String comment,
        boolean ownFeedback
) {
}
