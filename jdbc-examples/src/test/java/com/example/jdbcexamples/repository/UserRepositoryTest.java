package com.example.jdbcexamples.repository;

import com.example.jdbcexamples.dox.User;
import com.example.jdbcexamples.dto.UserAddressDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@SpringBootTest
@Slf4j
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Test
    void save() {
        /*builder和new的区别；原子性*/
        var user = User.builder()
                .id("1284460380660002816")
                .name("yue")
                .build();
        userRepository.save(user);
    }

    @Test
    void delete() {
        var user = userRepository.findById("1284460380660002816").get();
        userRepository.delete(user);
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
    void findUserAddressResultSetExtractorById() {
        UserAddressDTO userAddressDTO = userRepository.findUserAddressResultSetExtractorById("1283957448725852160");
        log.debug("userAddressDTO: {}", userAddressDTO);
    }
}

