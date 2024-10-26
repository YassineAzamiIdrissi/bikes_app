package com.yassine.practice.Transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query("""
                SELECT transaction FROM Transaction transaction 
                WHERE transaction.user.id = :userId 
            """)
    Page<Transaction> findAllBorrowedBikes(Pageable pageable, Integer userId);

    @Query("""
                SELECT transaction FROM Transaction transaction 
                WHERE transaction.bike.owner.id = :userId 
                AND transaction.returned = true 
            """)
    Page<Transaction> findAllReturnedBikesToConnectedUser(Pageable pageable, Integer userId);

    @Query("""
                    SELECT (COUNT(*)>0) as isAlreadyBorrowedByOtherUser 
                    FROM Transaction transaction 
                    WHERE transaction.bike.id = :bikeId 
                    AND transaction.accepter = false
                    AND transaction.user.id != :userId
            """)
    boolean isAlreadyBorrowedByOtherUser(Integer bikeId, Integer userId);

    @Query("""
                    SELECT (COUNT(*)>0) as isAlreadyBorrowedByOtherUser 
                    FROM Transaction transaction 
                    WHERE transaction.bike.id = :bikeId 
                    AND transaction.user.id = :userId
                    AND transaction.returned = false
            """)
    boolean isAlreadyBorrowedBySameUser(Integer userId, Integer bikeId);

    @Query("""
                    SELECT  transaction FROM Transaction transaction 
                    WHERE transaction.bike.id = :bikeId 
                    AND transaction.user.id = :userId
                    AND transaction.returned = false
            """)
    Transaction findBikeByIdAndOwnerId(Integer userId, Integer bikeId);

    @Query("""
                SELECT transaction FROM Transaction transaction 
                WHERE transaction.bike.id = :bikeId 
                AND transaction.returned = true 
                AND transaction.accepter = false
                
            """)
    Optional<Transaction> findReturnedBikeByIdAndUserId(Integer bikeId);
}
