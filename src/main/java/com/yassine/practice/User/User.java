package com.yassine.practice.User;

import com.yassine.practice.ActivationCode.ActivationCode;
import com.yassine.practice.Authority.Authority;
import com.yassine.practice.Bike.Bike;
import com.yassine.practice.Favorites.Favorites;
import com.yassine.practice.Feedback.Feedback;
import com.yassine.practice.ResetPassword.ResetPasswordCode;
import com.yassine.practice.Transaction.Transaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "_user")
public class User implements Principal, UserDetails {
    @Id
    @GeneratedValue
    private Integer id;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;
    private String password;
    // security :
    private boolean locked;
    private boolean enabled;

    @Override
    public String getName() {
        return email;
    }

    // linking attributes :
    // --> authorities (many to many : )
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "auth_id"))
    private List<Authority> auths;
    // --> activation codes (one to many : )
    @OneToMany(mappedBy = "user")
    private List<ActivationCode> activationCodes;
    // --> reset password codes :
    @OneToMany(mappedBy = "user")
    private List<ResetPasswordCode> resetPasswordCodes;
    // --> bikes :
    @OneToMany(mappedBy = "owner")
    private List<Bike> bikes;
    // --> transactions :
    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;
    // --> feedback :
    @OneToMany(mappedBy = "user")
    private List<Feedback> feedbacks;
    // --> favorites ;
    @OneToMany(mappedBy = "user")
    private List<Favorites> favorites;
    // auditing :
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return auths.stream().
                map(r -> new SimpleGrantedAuthority(r.getAuthority())).
                collect(Collectors.toList());
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    // utility method :
    public String userFullName() {
        return firstname + " " + lastname;
    }
}
