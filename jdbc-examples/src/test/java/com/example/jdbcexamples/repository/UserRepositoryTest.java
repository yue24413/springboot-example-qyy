package com.example.jdbcexamples.repository;

import com.example.jdbcexamples.dox.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        int pageSize = 5;//每页显示数
        int page = 4;//共有几页
        //offset索引从0开始，表示第一页
        userRepository.findAll((page-1) * pageSize, pageSize)
                .forEach(user -> log.debug("user: {}", user));
        }

    @Test
    void findAll_2() {
       int pageSize = 5;
       int page = 4;
       Pageable pageable = PageRequest.of(page-1,pageSize);
        userRepository.findAll_2(pageable)
                .forEach(user -> log.debug("user:{}",user));
    }

    @Test
    void findAllByIdDesc() {
        int pageSize = 5;
        int page = 1;
        Pageable pageable = PageRequest.of(page-1,pageSize);
        userRepository.findAllByIdDesc(pageable)
                .forEach(user -> log.debug("user:{}",user));

    }

    @Test
    void findAddressUser() {

    }

    @Test
    void findAddressUserByUid() {
    }
}
