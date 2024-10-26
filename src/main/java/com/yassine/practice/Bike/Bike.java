package com.yassine.practice.Bike;

import com.yassine.practice.Favorites.Favorites;
import com.yassine.practice.Feedback.Feedback;
import com.yassine.practice.Transaction.Transaction;
import com.yassine.practice.User.User;
import com.yassine.practice.Utility.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@SuperBuilder
public class Bike extends BaseEntity {
    private String bikeName;
    private String techIdentifier;
    private String description;
    private String picture;
    private boolean shareable;
    private boolean repairing;
    // user :
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "bike")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "bike")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "bike")
    private List<Favorites> favorites;

    // utility methods :
    public double calculateRating() {
        double rating = feedbacks.stream().
                mapToDouble(Feedback::getRate).
                average().orElse(0.0);
        return Math.round(rating * 10.0) / 10.0;
    }

    public boolean isBorrowed() {
        boolean borrowed = false;
        for (Transaction transaction : transactions) {
            if (Objects.equals(transaction.getBike(), this) &&
                    !transaction.isReturned()) {
                borrowed = true;
                break;
            }
        }
        return borrowed;
    }

    public boolean isFavorite(User user) {
        boolean isFav = false;
        for (Favorites favorite : favorites) {
            if (Objects.equals(favorite.getUser().getId(), user.getId()) &&
                    Objects.equals(favorite.getBike().getId(), this.getId())) {
                isFav = true;
                break;
            }
        }
        return isFav;
    }
}
