package com.yassine.practice.Feedback;

import com.yassine.practice.Bike.Bike;
import com.yassine.practice.User.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FeedbackMapper {
    public Feedback reqToFeedback(
            FeedbackRequest req, Integer userId
    ) {
        User user = new User();
        user.setId(userId);
        Bike bike = new Bike();
        bike.setId(req.getBikeId());
        return Feedback.builder().
                user(user).
                bike(bike).
                comment(req.getComment()).
                rate(req.getMark()).
                build();
    }

    public FeedbackResponse feedbackToResponse(Feedback feedback,
                                               Integer userId) {
        return FeedbackResponse.builder().
                comment(feedback.getComment()).
                username(feedback.getUser().userFullName()).
                ownComment(Objects.equals(feedback.getUser().getId(), userId)).
                build();
    }
}
