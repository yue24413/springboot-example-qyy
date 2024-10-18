package com.example.springmvcexamples.controller;

import com.example.springmvcexamples.service.UserService;
import com.example.springmvcexamples.dox.User;
import com.example.springmvcexamples.exception.Code;
import com.example.springmvcexamples.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class LoginController {
    //需要userservice进行校验
    private final UserService userService;
    @PostMapping("login")
    public ResultVO login(@RequestBody User user) {
        User userR = userService.getUserByAccount(user.getAccount(),user.getPassword());
        if (userR == null) {
            return  ResultVO.error(Code.LOGIN_ERROR);
        }
        return  ResultVO.success(userR);

    }
}
/*用脚本进行测试，没必要写页面*/