package com.example.redisexamples.component;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class UILDTest {
    @Autowired
    private ULID ulid;
    @Test
    void nextTest() {
        //01JCCFJFTGBYBFGXRCE4CZN58S
        log.debug(ulid.nextULID());
        //01JCCFJFTHBT7204DRWB4Q58TQ
        log.debug(ulid.nextULID());
        //01JCCFJFTJJ69VRHR3WASPG02H
        log.debug(ulid.nextULID());
    }
}