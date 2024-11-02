package com.example.springmvcexamples.dox;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    public static final String USER = "uko7";
    public static final String ADMIN = "qwhv";
    private String id;
    private String name;
    /*即使是密码加密后，也不应该返给客户端，加忽略*/
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String account;
    private String role;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}


