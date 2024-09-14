package com.example.jdbcexamples.repository;

import com.example.jdbcexamples.dox.Address;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;
    @Test
    void findByUserId() {
        for (Address address : addressRepository.findByUserId("1283957448725852160")) {
            log.debug(address.toString());
        }
    }

    @Test
    void save() {
        var address = Address.builder().userId("1283957448725852160").detail("666").build();
        addressRepository.save(address);
    }
}