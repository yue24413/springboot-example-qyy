package com.example.springmvcexamples.service;

import com.example.springmvcexamples.dox.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class UserServiceTest {
    @Autowired
    private  UserService userService;
    @Test
    void listUsers() {
        for (User listUser : userService.listUsers()) {
            log.info(listUser.toString());

        }
    }
}