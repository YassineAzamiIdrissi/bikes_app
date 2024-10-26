package com.yassine.practice.Bike;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BorrowedBikeResponse {
    private Integer id;
    private String ownerName;
    private String borrowedBy;
    private LocalDateTime borrowedDate;
    private String bikeName;
    private String techIdentifier;
    private boolean returned;
    private boolean accepted;
    private boolean isFavorite;
}
