package com.example.jdbcexamples.repository;

import com.example.jdbcexamples.dox.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAddressById(){
        userRepository.findAddressById("1530375756270878723").forEach(System.out::println);
    }
    @Test
    public void saveUser(){
        User user = User.builder().name("BO").build();
        userRepository.save(user);
    }
    @Test
    public void findById(){
        userRepository.findById("1530375756270878723")
                .ifPresent(user -> log.debug("{}",user));
    }

    @Test
    public void findAll(){
       int pageSize = 5;
       int page = 4;
        userRepository.findAll((page - 1) * pageSize, 5)
                .forEach(System.out::println);    }
}
