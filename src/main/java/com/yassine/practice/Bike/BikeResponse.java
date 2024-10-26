package com.yassine.practice.Bike;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BikeResponse {
    private Integer id;
    private String ownerName;
    private String bikeName;
    private String techIdentifier;
    private boolean isBorrowed;
    private String description;
    private byte[] picture;
    private double rating;
    private boolean shareable;
    private boolean repairing;
    private boolean isFavorite;
}
