package com.yassine.practice.Feedback;

import com.yassine.practice.Bike.Bike;
import com.yassine.practice.Bike.BikeMapper;
import com.yassine.practice.Bike.BikeRepository;
import com.yassine.practice.CustomExceptions.BikeNotFoundException;
import com.yassine.practice.CustomExceptions.OperationNotPermittedException;
import com.yassine.practice.Paging.PageResponse;
import com.yassine.practice.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepo;
    private final FeedbackMapper feedbackMapper;
    private final BikeRepository bikeRepo;

    // add a new feedback :
    public Integer addFeedback(FeedbackRequest feedbackReq,
                               Authentication authentication) {
        Integer bikeId = feedbackReq.getBikeId();
        Bike concernedBike = bikeRepo.findById(bikeId).orElseThrow(
                () -> new BikeNotFoundException
                        ("bike with id " + bikeId + " not found")
        );
        User user = (User) authentication.getPrincipal();
        if (Objects.equals(concernedBike.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException
                    ("You can't feedback your own bikes !");
        }
        Feedback feedback = feedbackMapper.reqToFeedback
                (feedbackReq, user.getId());
        return feedbackRepo.save(feedback).getId();
    }

    public PageResponse<FeedbackResponse> getAllFeedbacksOfBike(
            int bikeId,
            int page,
            int size,
            Authentication authentication
    ) {
        Bike concernedBike = bikeRepo.findById(bikeId).orElseThrow(
                () -> new BikeNotFoundException("bike with id " + bikeId + " not found")
        );
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("createdDate").descending()
        );
        Page<Feedback> feedbacks =
                feedbackRepo.findAllFeedbacksOfBike(pageable,
                        concernedBike.getId()
                );
        User user = (User) authentication.getPrincipal();
        List<FeedbackResponse> content =
                feedbacks.stream().
                        map((fb) -> feedbackMapper.feedbackToResponse(
                                fb, user.getId()
                        )).
                        toList();
        return new PageResponse<>(
                content,
                page,
                size,
                feedbacks.getTotalPages(),
                feedbacks.getTotalElements(),
                feedbacks.isFirst(),
                feedbacks.isLast()
        );
    }
}
