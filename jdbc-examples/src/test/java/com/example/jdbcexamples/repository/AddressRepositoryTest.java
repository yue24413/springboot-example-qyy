package com.example.jdbcexamples.repository;

import com.example.jdbcexamples.dox.Address;
import com.example.jdbcexamples.dto.AddressUser2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Test
    void findAddressUserById() {
        addressRepository.findAddressUserById("1284461669104054272").forEach(address -> log.debug("detail:{}",address.getDetail().toString()));
    }

//    @Test
//    void findAddressUser2ById() {
//        AddressUser2 addressUser2 = addressRepository.findAddressUser2ById("1284461863585542144");
//        log.debug("addressUser2.getUser(): {}",addressUser2.getUser());
//        log.debug("addressUser2.getAddress(): {}",addressUser2.getAddress());
//
//    }
}

