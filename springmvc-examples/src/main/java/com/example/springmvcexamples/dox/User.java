package com.example.springmvcexamples.dox;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String id;
    private String name;
    private String password;
    private String account;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}


