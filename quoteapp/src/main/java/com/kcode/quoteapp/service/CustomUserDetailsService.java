package com.kcode.quoteapp.service;


import com.kcode.quoteapp.entity.AuthRequest;
import com.kcode.quoteapp.entity.Quote;
import com.kcode.quoteapp.entity.RegisterRequest;
import com.kcode.quoteapp.entity.User;
import com.kcode.quoteapp.repository.QuoteRepository;
import com.kcode.quoteapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>());
    }


    public User saveUser(RegisterRequest request) {
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(bcryptEncoder.encode(request.getPassword()));
        userRepository.save(newUser);
        return newUser;
    }

}
