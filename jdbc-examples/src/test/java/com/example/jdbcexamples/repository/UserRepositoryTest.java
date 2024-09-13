package com.example.jdbcexamples.repository;

import com.example.jdbcexamples.dox.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Slf4j
class UserRepositoryTest {

@Autowired
private UserRepository userRepository;


    @Test
    void save() {
        var user = User.builder()
                .name("SUN")
                .build();
        userRepository.save(user);
    }


    @Test
    void findById() {
        userRepository.findById("1283957448725852160")
                .ifPresent(user -> log.debug("user: {}", user));
    }
}