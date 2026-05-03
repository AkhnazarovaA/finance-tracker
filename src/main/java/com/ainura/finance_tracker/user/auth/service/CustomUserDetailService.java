package com.ainura.finance_tracker.user.auth.service;

import com.ainura.finance_tracker.exception.UserException;
import com.ainura.finance_tracker.user.model.entity.UserEntity;
import com.ainura.finance_tracker.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findByEmail(email)
                .orElseThrow(() -> new UserException("User not found"));

        return User.builder()
                .username(userEntity.getEmail())
                .password(userEntity.getPassword())
                .authorities(new SimpleGrantedAuthority("ROLE_" + userEntity.getUserRole()))
                .build();
    }

}
