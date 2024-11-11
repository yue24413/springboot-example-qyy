package com.example.backendexamples.repository;

import com.example.backendexamples.dox.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class UserRepositoryTest {
@Autowired
    public UserRepository userRepository;
    @Test
    void findByAccount() {
        User user = userRepository.findByAccount("admin");
        log.info(user.toString());
    }
}