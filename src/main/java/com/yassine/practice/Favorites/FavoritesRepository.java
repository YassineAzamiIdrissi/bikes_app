package com.yassine.practice.Favorites;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FavoritesRepository extends JpaRepository<Favorites, Integer> {

    @Query("""
                SELECT (COUNT(*) > 0) as isAlreadyInFavorites 
                FROM Favorites favorite WHERE 
                favorite.user.id = :userId 
                AND 
                favorite.bike.id = :bikeId 
            """)
    boolean isAlreadyInFavorites(Integer bikeId, Integer userId);

    @Transactional
    @Modifying
    @Query("""
                    DELETE FROM Favorites favorite 
                    WHERE favorite.user.id = :userId 
                    AND favorite.bike.id = :bikeId
            """)
    void deleteFavorites(Integer bikeId, Integer userId);

    @Query("""
                        SELECT favorites FROM Favorites favorites 
                        WHERE favorites.user.id = :userId 
            """)
    Page<Favorites> getAllFavoritesOfThisUser(Pageable pageable, Integer userId);
}
