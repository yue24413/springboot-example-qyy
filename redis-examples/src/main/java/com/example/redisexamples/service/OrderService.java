package com.example.redisexamples.service;

import com.example.redisexamples.component.ULID;
import com.example.redisexamples.dto.Item;
import com.example.redisexamples.dto.Order;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.redisson.api.stream.StreamAddArgs;
import org.redisson.client.codec.IntegerCodec;
import org.redisson.client.codec.StringCodec;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final RedissonClient client;
    private final ULID ulid;
    public void addItems(List<Item> items) {
    //不用利用for每次执行insert指令，利用批处理对象
        RBatch batch = client.createBatch();
        for (Item item : items) {
            RMapAsync<String, Integer> map = batch.getMap(Item.PRE_KEY + item.getId(), IntegerCodec.INSTANCE);
            map.putAsync("total", item.getTotal());
        }
        //统一传
        batch.execute();
    }
    public long buy(String itemId,String userId){
        //传参，往限流要传数，不能序列化
        long q = client.getFunction()
                //类型，调用哪个函数，返回类型，key
                .call(FunctionMode.WRITE,
                        "buy_58",
                        FunctionResult.LONG,
                        List.of((Item.PRE_KEY) + itemId));
        if(q == -1){
            log.debug("抢光了");
            return -1;
        }
        var id =  ulid.nextULID();
        //没抢完，生成订单，注入随机id
        Order o = Order.builder()
                .id(id)
                .userId(userId)
                .itemId(itemId)
                .build();
        client.getBucket(Order.PRE_KEY + id)
                .set(o);
        sendMessage(o);
        return q;
    }
    private void sendMessage(Order order) {
        // 消息ID类型；消息体类型
        client.getStream(Order.STREAM_KEY, StringCodec.INSTANCE)
        // 仅需在消息体添加订单ID
            .add(StreamAddArgs.entry("orderid", order.getId()));

    }
}
