package com.yassine.practice.Feedback;

import com.yassine.practice.Bike.Bike;
import com.yassine.practice.User.User;
import com.yassine.practice.Utility.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class Feedback extends BaseEntity {
    private String comment;
    private Double rate;
    // feedback :
    @ManyToOne
    @JoinColumn(name = "bike_id")
    private Bike bike;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
