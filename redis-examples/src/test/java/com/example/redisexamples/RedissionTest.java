package com.example.redisexamples;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class RedissionTest {
    @Autowired
    private RedissonClient redisson;
    @Test
    public void testRedisson() {
        var name = "BO";
        var key = "user:14";
        //桶，不希望值以二进制的形式塞，要以可读的，字符串塞入
        RBucket<String> bucket = redisson.getBucket(key, StringCodec.INSTANCE);
        log.info("bucket = {}", bucket.isExists());
        bucket.set(name);
        RBucket<String> bucket1 = redisson.getBucket(key, StringCodec.INSTANCE);
        log.info("bucket1 = {}", bucket1);

    }
}
