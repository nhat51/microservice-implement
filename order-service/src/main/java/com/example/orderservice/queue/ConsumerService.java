package com.example.orderservice.queue;

import com.example.orderservice.entity.Order;
import com.example.orderservice.entityDTO.OrderDto;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.status.Status;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import static com.example.orderservice.queue.Config.*;

@Component
public class ConsumerService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Transactional
    public void handleMessage(OrderDto orderDto){
        System.out.println(orderDto.toString());
      Order exist = orderRepository.getById(orderDto.getOrderId());
        if (exist == null){
            return;
        }
        if (!orderDto.getInventoryStatus().equals(Status.InventoryStatus.PENDING.name())){
            System.out.println("1"+orderDto.getInventoryStatus());
            exist.setInventory_status(orderDto.getInventoryStatus());
        }
        if (!orderDto.getPaymentStatus().equals(Status.PaymentStatus.PENDING.name())){
            System.out.println("2"+orderDto.getPaymentStatus());
            exist.setPayment_status(orderDto.getPaymentStatus());
        }
        handleQueueInvent(orderDto);
    }

    @Transactional
    public void handleQueueInvent(OrderDto orderDto){
       Order order = orderRepository.getById(orderDto.getOrderId());
        if (order.getPayment_status().equals(Status.PaymentStatus.UNPAID.name())
                && order.getInventory_status().equals(Status.InventoryStatus.DONE.name())){
            orderDto.setInventoryStatus(Status.InventoryStatus.RETURN.name());
            rabbitTemplate.convertAndSend(DIRECT_EXCHANGE,DIRECT_ROUTING_KEY_INVENT,orderDto);
            return;
        }
       if (order.getInventory_status().equals(Status.InventoryStatus.OUT_OF_STOCK.name())
            && order.getPayment_status().equals(Status.PaymentStatus.PAID.name())){

           try {
               orderDto.setPaymentStatus(Status.PaymentStatus.REFUND.name());
               rabbitTemplate.convertAndSend(DIRECT_EXCHANGE,DIRECT_ROUTING_KEY_PAY,orderDto);
               return;

           }catch (Exception e){

           }
       }
    }
  /*  @Transactional
    public void handleQueuePayment(OrderDto orderDto){
        Order order = orderRepository.getById(orderDto.getOrderId());

        if (orderDto.getPaymentStatus().equals(Status.PaymentStatus.UNPAID.name())){
            orderDto.setInventoryStatus(Status.InventoryStatus.RETURN.name());
            order.setStatus(orderDto.getOrderStatus());
            order.setPayment_status(orderDto.getPaymentStatus());
            orderRepository.save(order);
            rabbitTemplate.convertAndSend(DIRECT_EXCHANGE,DIRECT_ROUTING_KEY_INVENT,orderDto);
            return;
        }

            order.setStatus(orderDto.getOrderStatus());
            order.setPayment_status(orderDto.getPaymentStatus());
            orderRepository.save(order);

    }*/
}
