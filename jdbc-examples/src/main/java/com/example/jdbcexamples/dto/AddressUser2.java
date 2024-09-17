package com.example.jdbcexamples.dto;

import com.example.jdbcexamples.dox.Address;
import com.example.jdbcexamples.dox.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressUser2 {
    private User user;
    private Address address;
}
