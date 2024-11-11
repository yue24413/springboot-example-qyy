package com.example.redisexamples.service;

import com.example.redisexamples.dto.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

@SpringBootTest
@Slf4j
class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @Test
    void addItems() {
        List<Item> items = List.of(
                Item.builder().id("01JCCFJFTGBYBFGXRCE4CZN58S").total(50).build(),
                Item.builder().id("01JCCFJFTHBT7204DRWB4Q58TQ").total(30).build()
        );
        orderService.addItems(items);
    }

    @Test
    void buy() throws InterruptedException {
    var count = 200;//模拟多线程，很多人买
        CountDownLatch latch = new CountDownLatch(count);
        var romdom = new Random();//随机数，某个人
        for (int i = 0; i < count; i++) {
            Thread.startVirtualThread(() -> {
                long buy = orderService.buy("01JCCFJFTGBYBFGXRCE4CZN58S", String.valueOf(romdom.nextInt(count)));
                log.debug("{}",buy);
                latch.countDown();
            });
        }
        latch.await();
        Thread.sleep(6000);
    }
}
