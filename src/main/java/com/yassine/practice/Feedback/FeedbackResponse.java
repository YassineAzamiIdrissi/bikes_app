package com.yassine.practice.Feedback;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FeedbackResponse {
    private String username;
    private String comment;
    private boolean ownComment;
}
