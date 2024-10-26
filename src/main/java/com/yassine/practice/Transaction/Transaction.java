package com.yassine.practice.Transaction;

import com.yassine.practice.Bike.Bike;
import com.yassine.practice.User.User;
import com.yassine.practice.Utility.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class Transaction extends BaseEntity {
    private boolean returned;
    private boolean accepter;
    // user :
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bike_id")
    private Bike bike;
}
