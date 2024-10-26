package com.yassine.practice.Specifications;


import com.yassine.practice.Bike.Bike;
import org.springframework.data.jpa.domain.Specification;

public class BikeSpecification {
    public static Specification<Bike> withOwnerId(Integer ownerId) {
        return (root, query, cb) -> cb.equal(root.get("owner").get("id"), ownerId);
    }
}
