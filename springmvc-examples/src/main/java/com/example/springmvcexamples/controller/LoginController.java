package com.example.springmvcexamples.controller;

import com.example.springmvcexamples.component.JWTComponent;
import com.example.springmvcexamples.component.PasswordEncoderConfig;
import com.example.springmvcexamples.service.UserService;
import com.example.springmvcexamples.dox.User;
import com.example.springmvcexamples.exception.Code;
import com.example.springmvcexamples.vo.ResultVO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class LoginController {
    //需要userservice进行校验
    private final UserService userService;
    private final PasswordEncoder passwordEnder;
    private JWTComponent jwtComponent
    @PostMapping("login")
    public ResultVO login(@RequestBody User user, HttpServletResponse response) {
//        User userR = userService.getUserByAccount(user.getAccount(),user.getPassword());
        User userR = userService.getUserByAccount(user.getAccount());

        if (userR == null || ) {
            return  ResultVO.error(Code.LOGIN_ERROR);
        }
        String token = jwtComponent.encode(Map.of("uid",userR.getId(),"role",user.getRole()));
        response.setHeader("token", token);
        response.setHeader("role", user.getRole());
        return  ResultVO.success(userR);

    }
}
/*用脚本进行测试，没必要写页面*/