package com.example.jdbcexamples.dto;

import com.example.jdbcexamples.dox.Address;
import com.example.jdbcexamples.dox.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressUserDTO {
    private User user;
    private Address address;
}
