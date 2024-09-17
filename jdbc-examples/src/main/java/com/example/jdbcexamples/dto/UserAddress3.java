package com.example.jdbcexamples.dto;

import com.example.jdbcexamples.dox.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAddress3 {
    private String id;
    private String name;
    private String detail;
    private List<Address> addresses;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
