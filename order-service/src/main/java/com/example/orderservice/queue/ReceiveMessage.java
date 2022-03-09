package com.example.orderservice.queue;

import com.example.orderservice.entityDTO.OrderDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.orderservice.queue.Config.QUEUE_ORDER;

@Component
public class ReceiveMessage {

    @Autowired
    ConsumerService consumerService;

    @RabbitListener(queues = {QUEUE_ORDER})
    public void receiveMessage(OrderDto orderDto){
        consumerService.handleMessage(orderDto);
        System.out.println("Đã xử lý order và lưu vào db");
    }
}
