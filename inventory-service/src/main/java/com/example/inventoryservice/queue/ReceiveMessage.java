package com.example.inventoryservice.queue;

import com.example.inventoryservice.dto.OrderDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.inventoryservice.queue.Config.QUEUE_INVENT;

@Component
public class ReceiveMessage {
    @Autowired
    ConsumerService service;

    @RabbitListener(queues = {QUEUE_INVENT})
    public void getInfo(OrderDto orderDto){
        System.out.println("Nhận được rồi tư từ rồi xử lý");
        service.handleMessage(orderDto);
        System.out.println("Xong");

    }
}
