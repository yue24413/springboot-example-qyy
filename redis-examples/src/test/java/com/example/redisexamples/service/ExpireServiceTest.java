package com.example.redisexamples.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ExpireServiceTest {
    @Autowired
    private ExpireService expireService;
    @Test
    void expire() throws InterruptedException {
        for (int i = 0; i < 11; i++) {
            boolean expire = expireService.expire("api:522",5,2);
            log.info("expire:{}", expire);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}