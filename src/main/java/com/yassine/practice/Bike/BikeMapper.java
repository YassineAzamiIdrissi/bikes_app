package com.yassine.practice.Bike;

import com.yassine.practice.Transaction.Transaction;
import com.yassine.practice.User.User;
import org.springframework.stereotype.Service;

import static com.yassine.practice.FilesHandling.FileUtils.readFileBytes;

@Service
public class BikeMapper {
    public Bike reqToBike(BikeRequest bikeReq) {
        return Bike.builder().
                id(bikeReq.getId()).
                bikeName(bikeReq.getBikeName()).
                description(bikeReq.getDescription()).
                techIdentifier(bikeReq.getTechIdentifier()).
                shareable(bikeReq.isShareable()).
                build();
    }

    public BikeResponse bikeToResponse(Bike bike) {
        return BikeResponse.builder().
                id(bike.getId()).
                ownerName(bike.getOwner().userFullName()).
                bikeName(bike.getBikeName()).
                techIdentifier(bike.getTechIdentifier()).
                isBorrowed(bike.isBorrowed()).
                description(bike.getDescription()).
                rating(bike.calculateRating()).
                picture(readFileBytes(bike.getPicture())).
                shareable(bike.isShareable()).
                repairing(bike.isRepairing()).
                build();
    }

    public BikeResponse bikeToResponse(Bike bike, User user) {
        return BikeResponse.builder().
                id(bike.getId()).
                ownerName(bike.getOwner().userFullName()).
                bikeName(bike.getBikeName()).
                techIdentifier(bike.getTechIdentifier()).
                description(bike.getDescription()).
                rating(bike.calculateRating()).
                picture(readFileBytes(bike.getPicture())).
                shareable(bike.isShareable()).
                isFavorite(bike.isFavorite(user)).
                repairing(bike.isRepairing()).
                build();
    }

    public BorrowedBikeResponse transactionToBorrowedBikeResponse
            (Transaction transaction) {
        return BorrowedBikeResponse.builder().
                id(transaction.getBike().getId()).
                ownerName(transaction.getBike().getOwner().userFullName()).
                borrowedBy(transaction.getUser().userFullName()).
                bikeName(transaction.getBike().getBikeName()).
                borrowedDate(transaction.getCreatedDate()).
                techIdentifier(transaction.getBike().getTechIdentifier()).
                returned(transaction.isReturned()).
                accepted(transaction.isAccepter()).
                build();
    }

    public BorrowedBikeResponse transactionToBorrowedBikeResponse
            (Transaction transaction, User user) {
        return BorrowedBikeResponse.builder().
                id(transaction.getBike().getId()).
                ownerName(transaction.getBike().getOwner().userFullName()).
                borrowedBy(transaction.getUser().userFullName()).
                bikeName(transaction.getBike().getBikeName()).
                borrowedDate(transaction.getCreatedDate()).
                techIdentifier(transaction.getBike().getTechIdentifier()).
                returned(transaction.isReturned()).
                isFavorite(transaction.getBike().isFavorite(user)).
                accepted(transaction.isAccepter()).
                build();
    }
}
