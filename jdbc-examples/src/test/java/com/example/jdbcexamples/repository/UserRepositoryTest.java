package com.example.jdbcexamples.repository;

import com.example.jdbcexamples.dox.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
@Slf4j
class UserRepositoryTest {

@Autowired
private UserRepository userRepository;


    @Test
    void save() {
        var user = User.builder()
                .name("Yue")
                .build();
        userRepository.save(user);
    }


    @Test
    void findById() {
        userRepository.findById("1283957448725852160")
                .ifPresent(user -> log.debug("user: {}", user));
    }


    @Test
    void findByname() {
        userRepository.findByname("SUN").getId();
    }

    @Test
    void findAll() {
        int pageSize = 5;
        int page = 4;
        userRepository.findAll((page-1) * pageSize, pageSize)
                .forEach(user -> log.debug("user: {}", user));
        }

}
