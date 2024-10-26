package com.yassine.practice.Bike;

import com.yassine.practice.Favorites.FavoritesResponse;
import com.yassine.practice.Paging.PageResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("bikes")
@Tag(name = "bike")
public class BikeController {
    private final BikeService bikeService;

    @PostMapping
    ResponseEntity<Integer> saveNewBike
            (@RequestBody @Valid BikeRequest bikeRequest,
             Authentication authentication) {
        return ResponseEntity.ok().
                body(bikeService.saveNewBike(bikeRequest, authentication));
    }

    @GetMapping("{bike-id}")
    ResponseEntity<BikeResponse> getBikeById(@PathVariable("bike-id") Integer bikeId,
                                             Authentication authentication) {
        return ResponseEntity.ok().
                body(bikeService.getBikeById(bikeId, authentication));
    }

    @GetMapping
    ResponseEntity<PageResponse<BikeResponse>> getTimeLineBikes
            (
                    @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                    @RequestParam(name = "size", required = false, defaultValue = "5") int size,
                    Authentication authentication
            ) {
        return ResponseEntity.ok().
                body(bikeService.getTimeLineBikes(page, size, authentication));
    }


    @GetMapping("own-bikes")
    ResponseEntity<PageResponse<BikeResponse>> getMyOwnBikes
            (
                    @RequestParam(name = "page", required = false, defaultValue = "0")
                    int page,
                    @RequestParam(name = "size", required = false, defaultValue = "5")
                    int size,
                    Authentication authentication
            ) {
        return ResponseEntity.ok().
                body(bikeService.getOwnedBikes(page, size, authentication));
    }

    @GetMapping("borrowed")
    ResponseEntity<PageResponse<BorrowedBikeResponse>> getBorrowedBikes
            (
                    @RequestParam(name = "page", required = false, defaultValue = "0")
                    int page,
                    @RequestParam(name = "size", required = false, defaultValue = "5")
                    int size,
                    Authentication authentication
            ) {
        return ResponseEntity.ok().
                body(bikeService.getBorrowedBikes(page, size, authentication));
    }

    @GetMapping("returned")
    ResponseEntity<PageResponse<BorrowedBikeResponse>>
    findAllReturnedBikesToConnectedUser(
            @RequestParam(name = "page", required = false, defaultValue = "0") int
                    page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int
                    size,
            Authentication authentication
    ) {
        return ResponseEntity.ok().
                body(bikeService.getReturnedBikes(page, size, authentication));
    }

    @PatchMapping("shareable/{bike-id}")
    ResponseEntity<Integer> updateShareableStatus
            (@PathVariable("bike-id") Integer bikeId,
             Authentication authentication) {
        return ResponseEntity.ok().
                body(bikeService.updateShareableStatus(bikeId, authentication));
    }

    @PatchMapping("repairing/{bike-id}")
    ResponseEntity<Integer> updateRepairingStatus(
            @PathVariable("bike-id") Integer bikeId,
            Authentication authentication
    ) {
        return ResponseEntity.ok().
                body(bikeService.updateRepairingStatus(bikeId, authentication));
    }

    @PostMapping("borrow/{bike-id}")
    ResponseEntity<Integer> borrowBike(
            @PathVariable("bike-id") Integer bikeId,
            Authentication authentication
    ) {
        return ResponseEntity.ok().
                body(bikeService.borrowBike(bikeId, authentication));
    }

    @PatchMapping("return/{bike-id}")
    ResponseEntity<Integer> returnBike(
            @PathVariable("bike-id") Integer bikeId,
            Authentication authentication
    ) {
        return ResponseEntity.ok().
                body(bikeService.returnBike(bikeId, authentication));
    }

    @PatchMapping("approve/{bike-id}")
    ResponseEntity<Integer> approveBike(
            @PathVariable("bike-id") Integer bikeId,
            Authentication authentication
    ) {
        return ResponseEntity.ok().
                body(bikeService.approveBikeReturn(bikeId, authentication));
    }

    @PatchMapping(value = "picture/{bike-id}", consumes = "multipart/form-data")
    ResponseEntity<Integer> setBikePicture
            (
                    @PathVariable("bike-id") Integer bikeId,
                    @Parameter()
                    @RequestPart("file")
                    MultipartFile file,
                    Authentication authentication
            ) {
        return ResponseEntity.ok().
                body(bikeService.modifyBikePicture(bikeId, file, authentication));
    }

    @PostMapping("favorites/{bike-id}")
    ResponseEntity<Integer> addBikeToFavorites(
            @PathVariable("bike-id") Integer bikeId,
            Authentication authentication
    ) {
        return ResponseEntity.ok().
                body(bikeService.addBikeToFavorites(bikeId, authentication));
    }

    @DeleteMapping("favorites/{bike-id}")
    ResponseEntity<?> deleteBikeFromFavorites
            (@PathVariable("bike-id") Integer bikeId,
             Authentication authentication) {
        bikeService.removeFromFavorites(bikeId, authentication);
        return ResponseEntity.ok().build();
    }

    @GetMapping("favorites/belongs/{bike-id}")
    ResponseEntity<Boolean> isBikeInFavorites(
            @PathVariable("bike-id") Integer bikeId,
            Authentication authentication
    ) {
        return ResponseEntity.ok().
                body(bikeService.isBikeInFavorites(bikeId, authentication));
    }

    @GetMapping("favorites")
    ResponseEntity<PageResponse<FavoritesResponse>>
    readAllFavoritesOfThiUser(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "5", required = false) int size,
            Authentication authentication
    ) {
        return ResponseEntity.ok().
                body(
                        bikeService.getAllFavoritesOfThisUser(page, size, authentication)
                );
    }
}
