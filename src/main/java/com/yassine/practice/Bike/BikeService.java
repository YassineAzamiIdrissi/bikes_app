package com.yassine.practice.Bike;

import com.yassine.practice.CustomExceptions.BikeNotBorrowableException;
import com.yassine.practice.CustomExceptions.BikeNotFoundException;
import com.yassine.practice.CustomExceptions.BikeNotReturnedYetException;
import com.yassine.practice.CustomExceptions.OperationNotPermittedException;
import com.yassine.practice.Favorites.FavoriteMapper;
import com.yassine.practice.Favorites.Favorites;
import com.yassine.practice.Favorites.FavoritesRepository;
import com.yassine.practice.Favorites.FavoritesResponse;
import com.yassine.practice.FilesHandling.FileStorageService;
import com.yassine.practice.Paging.PageResponse;
import com.yassine.practice.Transaction.Transaction;
import com.yassine.practice.Transaction.TransactionRepository;
import com.yassine.practice.User.User;
import com.yassine.practice.notification.Notification;
import com.yassine.practice.notification.NotificationService;
import com.yassine.practice.notification.NotificationStatus;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

import static com.yassine.practice.Specifications.BikeSpecification.withOwnerId;
import static com.yassine.practice.notification.NotificationStatus.*;

@Service
@RequiredArgsConstructor
public class BikeService {
    private final BikeMapper bikeMapper;
    private final BikeRepository bikeRepo;
    private final TransactionRepository transactionRepo;
    private final FileStorageService fileService;
    private final FavoritesRepository favRepo;
    private final FavoriteMapper favMapper;

    private final NotificationService notificationService;

    // add a new bike :
    public Integer saveNewBike(BikeRequest bikeRequest,
                               Authentication authentication) {
        Bike bike = bikeMapper.reqToBike(bikeRequest);
        User user = (User) authentication.getPrincipal();
        bike.setOwner(user);
        return bikeRepo.save(bike).getId();
    }

    // get a bike by its id :
    public BikeResponse getBikeById(Integer bikeId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return bikeRepo.findById(bikeId).map((b) -> bikeMapper.bikeToResponse(
                        b, user
                )).
                orElseThrow(() -> new
                        BikeNotFoundException("Bike with id " + bikeId + " bot found"));
    }

    // timeline bikes :
    public PageResponse<BikeResponse> getTimeLineBikes(int page,
                                                       int size,
                                                       Authentication authentication) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("createdDate").descending()
        );
        User user = (User) authentication.getPrincipal();
        Page<Bike> timeLineBikes =
                bikeRepo.findTimeLineBikes(pageable, user.getId());
        List<BikeResponse> content =
                timeLineBikes.stream().
                        map(bike -> (bikeMapper.bikeToResponse(bike, user)
                        )).
                        toList();
        return new PageResponse<>(
                content,
                page,
                size,
                timeLineBikes.getTotalPages(),
                timeLineBikes.getTotalElements(),
                timeLineBikes.isFirst(),
                timeLineBikes.isLast()
        );
    }

    // bikes owned by connected user :
    public PageResponse<BikeResponse> getOwnedBikes(int page,
                                                    int size,
                                                    Authentication authentication) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("createdDate").descending()
        );
        User user = (User) authentication.getPrincipal();
        Page<Bike> ownedBikes = bikeRepo.findAll
                (withOwnerId(user.getId()), pageable);
        List<BikeResponse> content = ownedBikes.stream().
                map(bikeMapper::bikeToResponse).
                toList();
        return new PageResponse<>(
                content,
                page,
                size,
                ownedBikes.getTotalPages(),
                ownedBikes.getTotalElements(),
                ownedBikes.isFirst(),
                ownedBikes.isLast()
        );
    }

    // borrowed bikes :
    public PageResponse<BorrowedBikeResponse> getBorrowedBikes(
            int page,
            int size,
            Authentication authentication
    ) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("createdDate").descending()
        );
        User user = (User) authentication.getPrincipal();
        Page<Transaction> transactions = transactionRepo.
                findAllBorrowedBikes(pageable, user.getId());
        List<BorrowedBikeResponse> content =
                transactions.stream().
                        map(transaction -> (bikeMapper.transactionToBorrowedBikeResponse(transaction, user))).
                        toList();
        return new PageResponse<>(
                content,
                page,
                size,
                transactions.getTotalPages(),
                transactions.getTotalElements(),
                transactions.isFirst(),
                transactions.isLast()
        );
    }

    // find returned bikes to this user :
    public PageResponse<BorrowedBikeResponse> getReturnedBikes
    (int page,
     int size,
     Authentication authentication) {
        Pageable pageable = PageRequest.of(page,
                size,
                Sort.by("createdDate").descending());
        User user = (User) authentication.getPrincipal();
        Page<Transaction> transactions =
                transactionRepo.findAllReturnedBikesToConnectedUser(pageable,
                        user.getId());
        List<BorrowedBikeResponse> content =
                transactions.stream().map(
                                bikeMapper::transactionToBorrowedBikeResponse
                        ).
                        toList();
        return new PageResponse<>(
                content,
                page,
                size,
                transactions.getTotalPages(),
                transactions.getTotalElements(),
                transactions.isFirst(),
                transactions.isLast()
        );
    }

    // update shareable status :
    public Integer updateShareableStatus(Integer bikeId,
                                         Authentication authentication) {
        Bike concernedBike = bikeRepo.findById(bikeId).orElseThrow
                (() -> new BikeNotFoundException("Bike with id " + bikeId + " not found"));
        User user = (User) authentication.getPrincipal();
        if (!Objects.equals(concernedBike.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You can't modify bikes you don't possess");
        }
        concernedBike.setShareable(!concernedBike.isShareable());
        return bikeRepo.save(concernedBike).getId();
    }

    public Integer updateRepairingStatus(Integer bikeId,
                                         Authentication authentication) {
        Bike concernedBike = bikeRepo.findById(bikeId).
                orElseThrow(() -> new BikeNotFoundException
                        ("Bike with id " + bikeId + " not found"));
        User user = (User) authentication.getPrincipal();
        if (!Objects.equals(concernedBike.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException
                    ("You can't modify bikes you don't possess");
        }
        concernedBike.setRepairing(!concernedBike.isRepairing());
        return bikeRepo.save(concernedBike).getId();
    }

    public Integer borrowBike(int bikeId,
                              Authentication authentication) {
        Bike concernedBike = bikeRepo.findById(bikeId).orElseThrow(
                () -> new BikeNotFoundException("Bike with id " + bikeId + " not found")
        );
        User user = (User) authentication.getPrincipal();
        if (Objects.equals(concernedBike.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You can't borrow your own bikes !");
        }
        if (!concernedBike.isShareable() || concernedBike.isRepairing()) {
            throw new BikeNotBorrowableException("This bike can't be borrowed " +
                    "either for being under repairing or not shareable");
        }
        boolean isAlreadyBorrowedByOtherUser = transactionRepo.isAlreadyBorrowedByOtherUser
                (concernedBike.getId(), user.getId());
        boolean isAlreadyBorrowedBySameUser = transactionRepo.isAlreadyBorrowedBySameUser(
                user.getId(), concernedBike.getId()
        );
        if (isAlreadyBorrowedByOtherUser) {
            throw new OperationNotPermittedException
                    ("This bike has already been borrowed by another user");
        }
        if (isAlreadyBorrowedBySameUser) {
            throw new OperationNotPermittedException
                    ("You have already borrowed this bike");
        }
        Transaction transaction = Transaction.builder().
                user(user).
                bike(concernedBike).
                returned(false).
                accepter(false).
                build();
        notificationService.sendNotification(concernedBike.getOwner().getId(),
                Notification.builder().
                        status(BORROWED).
                        message("Your bike " + concernedBike.getBikeName() +
                                " has been borrowed bu user " + user.userFullName()).
                        bikeName(concernedBike.getBikeName()).
                        build());
        return transactionRepo.save(transaction).getId();
    }

    public Integer returnBike(Integer bikeId,
                              Authentication authentication) {
        Bike concernedBike = bikeRepo.findById(bikeId).
                orElseThrow(() -> new BikeNotFoundException
                        ("Bike with id " + bikeId + " not found"));
        User user = (User) authentication.getPrincipal();
        if (Objects.equals(concernedBike.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You can't return your own bike");
        }
        boolean isAlreadyBorrowedBySameUser = transactionRepo.isAlreadyBorrowedBySameUser(
                user.getId(), concernedBike.getId()
        );
        if (!isAlreadyBorrowedBySameUser) {
            throw new OpenApiResourceNotFoundException("You can't return this bike since you didn't borrow it previously !");
        }
        Transaction transaction = transactionRepo.findBikeByIdAndOwnerId(
                user.getId(), concernedBike.getId()
        );
        transaction.setReturned(true);
        var saved = transactionRepo.save(transaction);
        notificationService.sendNotification(
                concernedBike.getOwner().getId(),
                Notification.builder().
                        message("your bike " + concernedBike.getBikeName() + " is returned").
                        status(RETURNED).
                        bikeName(concernedBike.getBikeName()).
                        build());
        return saved.getId();
    }

    public Integer approveBikeReturn
            (Integer bikeId,
             Authentication authentication) {
        Bike concernedBike = bikeRepo.findById(bikeId).
                orElseThrow(() -> new BikeNotFoundException
                        ("Bike with id " + bikeId + " not found"));
        User user = (User) authentication.getPrincipal();
        if (!Objects.equals(concernedBike.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You can't approve the return " +
                    "of bikes you don't possess");
        }
        boolean isAlreadyBorrowedByOtherUser = transactionRepo.isAlreadyBorrowedByOtherUser(
                concernedBike.getId(), user.getId()
        );
        if (!isAlreadyBorrowedByOtherUser) {
            throw new OperationNotPermittedException("This bike isn't even borrowed ");
        }
        Transaction transaction = transactionRepo.findReturnedBikeByIdAndUserId
                (
                        concernedBike.getId()
                ).orElseThrow
                (
                        () -> new BikeNotReturnedYetException("This bike isn't yet returned " +
                                "by its borrower")
                );
        transaction.setAccepter(true);
        var saved = transactionRepo.save(transaction);
        notificationService.sendNotification(
                transaction.getCreatedBy(),
                Notification.builder().
                        bikeName(concernedBike.getBikeName()).
                        status(RETURN_APPROVED).
                        message("Your return of "
                                + concernedBike.getBikeName() + " has been approved ").
                        build());
        return saved.getId();
    }

    public Integer modifyBikePicture(Integer bikeId,
                                     MultipartFile file,
                                     Authentication authentication) {
        Bike concernedBike = bikeRepo.findById(bikeId).orElseThrow(
                () -> new BikeNotFoundException("Bike with id " + bikeId + " not found")
        );
        User user = (User) authentication.getPrincipal();
        if (!Objects.equals(concernedBike.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You can't modify bikes you don't " +
                    "possess");
        }
        String coverUrl = fileService.saveFile(
                file,
                user.getId()
        );
        concernedBike.setPicture(coverUrl);
        return bikeRepo.save(concernedBike).getId();
    }

    public Integer addBikeToFavorites(Integer id, Authentication authentication) {
        Bike concernedBike = bikeRepo.findById(id).orElseThrow(() ->
                new BikeNotFoundException("Bike with id " + id + " not found"));
        if (concernedBike.isRepairing() || !concernedBike.isShareable()) {
            throw new OperationNotPermittedException("You can't add this bike to your " +
                    "favorites, " +
                    "either for being under reparations or not shareable at all");
        }
        User user = (User) authentication.getPrincipal();
        if (Objects.equals(user.getId(), concernedBike.getOwner().getId())) {
            throw new OperationNotPermittedException("you can't add your own bike to " +
                    "favorites !");
        }
        boolean isAlreadyInFavorites = favRepo.isAlreadyInFavorites(
                concernedBike.getId(), user.getId()
        );
        if (isAlreadyInFavorites) {
            throw new OperationNotPermittedException
                    ("This bike is already in your favorites list !");
        }
        Favorites favorite = Favorites.builder().
                bike(concernedBike).
                user(user).
                build();
        return favRepo.save(favorite).getId();
    }

    public void removeFromFavorites(Integer bikeId, Authentication authentication) {
        Bike concernedBike = bikeRepo.findById(bikeId).
                orElseThrow(() -> new BikeNotFoundException
                        ("Bike with id " + bikeId + " not found"));
        User user = (User) authentication.getPrincipal();
        boolean isAlreadyInFavorites = favRepo.isAlreadyInFavorites(
                concernedBike.getId(), user.getId()
        );
        if (!isAlreadyInFavorites) {
            throw new OperationNotPermittedException("This bike isn't already in your favorites list !");
        }
        favRepo.deleteFavorites(concernedBike.getId(), user.getId());
    }

    public Boolean isBikeInFavorites(Integer bikeId, Authentication authentication) {
        Bike concernedBike = bikeRepo.findById(bikeId).
                orElseThrow(() -> new BikeNotFoundException("Bike with id " + bikeId + " not found"));
        User user = (User) authentication.getPrincipal();
        return favRepo.isAlreadyInFavorites(bikeId, user.getId());
    }

    public PageResponse<FavoritesResponse>
    getAllFavoritesOfThisUser(
            int page,
            int size,
            Authentication authentication
    ) {
        Pageable pageable = PageRequest.of
                (page, size, Sort.by("createdDate").descending());
        User user = (User) authentication.getPrincipal();
        Page<Favorites> favorites = favRepo.getAllFavoritesOfThisUser(
                pageable, user.getId()
        );
        List<FavoritesResponse> content =
                favorites.stream().map(
                        favMapper::favoriteToResponse
                ).toList();
        return new PageResponse<>
                (
                        content,
                        page,
                        size,
                        favorites.getTotalPages(),
                        favorites.getTotalElements(),
                        favorites.isFirst(),
                        favorites.isLast()
                );
    }
}
