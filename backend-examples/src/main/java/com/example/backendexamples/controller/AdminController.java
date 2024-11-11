package com.example.backendexamples.controller;

import com.example.backendexamples.dox.User;
import com.example.backendexamples.repository.UserRepository;
import com.example.backendexamples.service.UserService;
import com.example.backendexamples.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    @PostMapping("users")
    public ResultVO postUser(@RequestBody User user) {
        userService.addUser(user);
        return ResultVO.ok;
    }

    @PutMapping("user/{account}/password")
    public ResultVO putPassword(@PathVariable String account) {
        userService.updatePassword(account);
        return ResultVO.ok;
    }

}
