package com.example.paymentservice.queue;

import com.example.paymentservice.dto.OrderDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.paymentservice.queue.Config.QUEUE_PAYMENT;

@Component
public class ReceiveMessage {
    @Autowired
    ConsumerService consumerService;

    @RabbitListener(queues = {QUEUE_PAYMENT})
    public void getInfoOrder(OrderDto orderDto){
        System.out.println("Đang nhận message và chờ xử lý");
        consumerService.handlePayment(orderDto);
        System.out.println("đã nhận order và xử lý");
    }
}
