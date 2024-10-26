package com.yassine.practice.Security;

import com.yassine.practice.User.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername
            (String email) throws UsernameNotFoundException {
        return userRepo.findUserByEmail(email).orElseThrow
                (() -> new UsernameNotFoundException
                        ("User with email " + email + " not found"));
    }
}
