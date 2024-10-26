package com.yassine.practice.Favorites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FavoritesResponse {
    private Integer bikeId;
    private String bikeName;
    private String ownerName;
    private String bikeTechIdentifier;
    private boolean isBorrowed;
}
