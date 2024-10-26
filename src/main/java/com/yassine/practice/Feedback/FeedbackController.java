package com.yassine.practice.Feedback;

import com.yassine.practice.Paging.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("feedbacks")
@Tag(name = "feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping
    ResponseEntity<Integer> createdFeedback(
            @RequestBody @Valid FeedbackRequest feedbackReq,
            Authentication authentication
    ) {
        return ResponseEntity.ok().
                body(feedbackService.addFeedback(feedbackReq, authentication));
    }

    @GetMapping("bike/{bike-id}")
    ResponseEntity<PageResponse<FeedbackResponse>> readAllFeedbacksOfBike(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size,
            @PathVariable("bike-id") Integer bikeId,
            Authentication authentication
    ) {
        return ResponseEntity.ok().
                body(feedbackService.getAllFeedbacksOfBike(
                        bikeId,
                        page,
                        size,
                        authentication
                ));
    }
}
