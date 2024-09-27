package com.example.jdbcexamples.repository;

import com.example.jdbcexamples.dox.Address;
import com.example.jdbcexamples.dto.AddressUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class AddressRepositoryTest {

    @Autowired
    private UserRepository userRepository;
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
    void findAddressUserDTOById() {
        AddressUserDTO addressUserDTO = addressRepository.findAddressUserDTOById("1284461998734405632");
        log.debug("{}",addressUserDTO.toString());
    }
}

