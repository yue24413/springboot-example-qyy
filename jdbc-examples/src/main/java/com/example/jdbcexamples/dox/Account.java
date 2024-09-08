package com.example.jdbcexamples.dox;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    @Id
    @CreatedBy
    private String id;
    private String name;
    private String balance;
    @Version
    private Integer version;//版本
}
