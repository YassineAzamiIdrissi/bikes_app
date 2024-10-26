package com.yassine.practice.Favorites;

import org.springframework.stereotype.Service;

@Service
public class FavoriteMapper {
    public FavoritesResponse favoriteToResponse(Favorites favorite) {
        return FavoritesResponse.builder().
                ownerName(favorite.getBike().getOwner().userFullName()).
                bikeName(favorite.getBike().getBikeName()).
                bikeTechIdentifier(favorite.getBike().getTechIdentifier()).
                bikeId(favorite.getBike().getId()).
                isBorrowed(favorite.getBike().isBorrowed()).
                build();
    }
}
