package com.barreto.book_social_newtwork.feedback;

import com.barreto.book_social_newtwork.book.BookRepository;
import com.barreto.book_social_newtwork.common.PageResponse;
import com.barreto.book_social_newtwork.exception.OperationNotPermittedException;
import com.barreto.book_social_newtwork.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final BookRepository bookRepository;
    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;

    public Long saveFeedback(FeedbackRequest request, Authentication connectedUser) {

        var book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new EntityNotFoundException("Book could not be found"));

        if (book.isArchived() || !book.isShareable()) {
            throw new OperationNotPermittedException("The requested book can not be rated");
        }

        User user = (User) connectedUser.getPrincipal();
        if (book.getOwner().getId().equals(user.getId())) {
            throw new OperationNotPermittedException("You can not rated your own book");
        }

        Feedback feedback = feedbackMapper.toFeedback(request, book);

        return feedbackRepository.save(feedback).getId();
    }

    public PageResponse<FeedbackResponse> findAllFeedbacksByBook(Long bookId, int page,
                                               int size, Authentication connectedUser) {

        Pageable pageable = PageRequest.of(page, size);
        User user = (User) connectedUser.getPrincipal();

        Page<Feedback> feedbacks = feedbackRepository.findAllByBookId(bookId, pageable);

        List<FeedbackResponse> feedbackResponses = feedbacks.stream()
                .map(f -> feedbackMapper.toFeedbackResponse(f, user.getId()))
                .toList();

        return new PageResponse<>(
                feedbackResponses,
                feedbacks.getNumber(),
                feedbacks.getSize(),
                feedbacks.getTotalElements(),
                feedbacks.getTotalPages(),
                feedbacks.isFirst(),
                feedbacks.isLast()
        );
    }
}
