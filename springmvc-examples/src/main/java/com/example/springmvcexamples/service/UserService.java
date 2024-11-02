package com.example.springmvcexamples.service;

import com.example.springmvcexamples.dox.User;
import org.springframework.stereotype.Service;
import java.util.List;


//用户的业务层，密码校验在控制层
@Service
public class UserService {
    private static List<User> USERS = create();
    private static List<User> create(){
        User u1 = User.builder()
                .id("1")
                .name("BO")
                .account("1001")
                .role(User.ADMIN)
                .password("$2a$10$vbic.eN8nCmnzExjVIUUwOKsIAz0.NGEYC/IGwjWJHSCC8s37Kn9G")
                .build();
        User u2 = User.builder()
                .id("222")
                .name("SUN")
                .account("8008")
                .role(User.USER)
                .password("$2a$10$vbic.eN8nCmnzExjVIUUwOKsIAz0.NGEYC/IGwjWJHSCC8s37Kn9G")
                .build();
        return List.of(u1,u2);
    }
    //业务逻辑
    public List<User> listUsers(){
        return USERS;
    }
//    public User getUserByAccount(String account,String password){
//        return USERS.stream()
//                .filter(u -> u.getAccount().equals(account))
//                .filter(u -> u.getPassword().equals(password))
//                .findFirst()
//                .orElse(null);
//    }
    public User getUserByAccount(String account){
        return USERS.stream()
                .filter(u -> u.getAccount().equals(account))
                .findFirst()
                .orElse(null);
    }
}
