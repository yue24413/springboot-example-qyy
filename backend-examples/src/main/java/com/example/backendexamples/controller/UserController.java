package com.example.backendexamples.controller;

import com.example.backendexamples.dox.User;
import com.example.backendexamples.service.UserService;
import com.example.backendexamples.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/")
public class UserController {
    private final UserService userService;
    @PatchMapping("password")
    public ResultVO patchPassword(@RequestBody User user, @RequestAttribute("uid") String uid){
        userService.updatePasswordById(uid,user.getPassword());
        return ResultVO.ok;
    }
    @GetMapping("users")
    public ResultVO getUsers(){
        return ResultVO.success(userService.listUsers());
    }
}
