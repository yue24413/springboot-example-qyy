package com.example.springmvcexamples.controller;


import com.example.springmvcexamples.service.UserService;
import com.example.springmvcexamples.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/")
public class AdminController {
    private final UserService userService;

    @GetMapping("users")
    public ResultVO getUsers() {
        return  ResultVO.success(userService.listUsers());
    }
    @GetMapping("users/{account}")
    public ResultVO getUser(@PathVariable String account) {
        return ResultVO.success(userService.getUserByAccount(account));
    }


}
