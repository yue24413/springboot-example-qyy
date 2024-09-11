package com.example.jdbcexamples.repository;

import com.example.jdbcexamples.dox.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import java.awt.print.Pageable;

@SpringBootTest
@Slf4j
public class UserRepositoryTest {
    @Autowired  //自动装配（autowiring）Bean
    private UserRepository userRepository;

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
     void findAddressesById(){
        userRepository.findAddressesById("1530375756270878723")
                .forEach(System.out::println);
    }
    @Test
     void findAll(){
       int pageSize = 5;
       int page = 4;
        userRepository.findAll((page - 1) * pageSize, 5)
                .forEach(System.out::println);    }
    @Test
     void findByIdDesc(){
        int pageSize = 5;
        int page = 1;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        userRepository.findByIdDesc(pageable)
                .forEach(System.out::println);
    }




}
