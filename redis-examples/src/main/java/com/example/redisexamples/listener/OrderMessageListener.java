package com.example.redisexamples.listener;

import  com.example.redisexamples.dto.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
//流的listener接口,流的key的类型/消息的类型(消息Id类型/消息体类型)
public class OrderMessageListener implements StreamListener<String, ObjectRecord<String, String>> {

    private final RedisTemplate<String, String> template;
    @Override
    public void onMessage(ObjectRecord<String, String> message) {
        log.info("{}",message.getId());
        log.info("{}",message.getValue());
        // 通知redis，确认消费者消费了消息,与getFunction相似
        template.opsForStream().acknowledge(Order.GROUP_NAME, message);
        // 移除已消费消息。为测试没有移除
        // template.opsForStream().delete(Order.STREAM_KEY, message.getId());
    }
}
