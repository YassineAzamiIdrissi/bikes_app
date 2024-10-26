package com.yassine.practice.Bike;

import com.yassine.practice.Paging.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface BikeRepository extends JpaRepository<Bike, Integer>,
        JpaSpecificationExecutor<Bike> {

    @Query("""
                SELECT bike FROM Bike bike 
                WHERE bike.owner.id != :userId 
                AND bike.shareable = true 
                AND bike.repairing = false
            """)
    Page<Bike> findTimeLineBikes(Pageable pageable,
                                 Integer userId);
}
