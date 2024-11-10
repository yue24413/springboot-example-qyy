package com.example.backendexamples.service;

import com.example.backendexamples.dox.User;
import com.example.backendexamples.exception.Code;
import com.example.backendexamples.exception.XException;
import com.example.backendexamples.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public User getUser(String account) {
        return userRepository.findByAccount(account);
    }
    //重置
    public void updatePassword(String account) {
        User user = userRepository.findByAccount(account);
        if(user == null) {
          throw  XException.builder()
                  .number(Code.ERROR)
                  .message("用户不存在")
                  .build();
        }
        user.setPassword(passwordEncoder.encode(account));
        userRepository.save(user);
    }

    @Transactional
    public void updatePasswordById(String uid, String password) {
        User user = userRepository.findById(uid).orElse(null);
        if(user == null) {
            throw  XException.builder()
                    .number(Code.ERROR)
                    .message("用户不存在")
                    .build();
        }
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Transactional
    public void  addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getAccount()));
        userRepository.save(user);
    }
    public List<User> listUsers() {
        return  userRepository.findAll();
    }



}
