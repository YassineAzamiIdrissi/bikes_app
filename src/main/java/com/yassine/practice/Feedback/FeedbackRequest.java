package com.yassine.practice.Feedback;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FeedbackRequest {
    @NotNull(message = "bike Id has to be specified")
    @Positive
    @Min(value = 1, message = "current sql indexes start from 1")
    private Integer bikeId;
    @NotNull(message = "comment has to be specified")
    @NotEmpty(message = "comment mustn't be empty")
    @NotBlank(message = "comment mustn't be blank")
    private String comment;
    @NotNull(message = "mark has to be specified")
    @Positive
    @Min(value = 0, message = "mark has a minimum of 1")
    @Max(value = 5, message = "mark has a maximum of 5")
    private double mark;
}
