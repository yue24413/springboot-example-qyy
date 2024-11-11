package com.example.backendexamples.service;

import com.example.backendexamples.dox.User;
import com.example.backendexamples.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InitService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        String account = "admin";
        long count = userRepository.count();
        if(count > 0 ){
            return;
        }
        User u = User.builder()
                .name(account)
                .account(account)
                .role(User.ADMIN)
                .password(passwordEncoder.encode(account))
                .build();
        userRepository.save(u);
    }



}
