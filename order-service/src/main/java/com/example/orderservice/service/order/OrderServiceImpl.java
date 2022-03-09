package com.example.orderservice.service.order;

import com.example.orderservice.entity.Cart;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderDetail;
import com.example.orderservice.repository.CartRepository;
import com.example.orderservice.repository.CustomerRepository;
import com.example.orderservice.repository.OrderDetailRepository;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.response.ResponseApi;
import com.example.orderservice.specification.ObjectFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    public ResponseApi listOrder(ObjectFilter objectFilter) {
        PageRequest paging = PageRequest.of(objectFilter.getPage()-1, objectFilter.getPageSize());
        Page<Order> data = orderRepository.findAll(paging);
        return new ResponseApi(HttpStatus.OK,"success",data);
    }

    @Override
    public Order createOrder(String access_token,Order order) {
        Order newOrder = new Order();
        newOrder.setShipName(order.getShipName());
        newOrder.setCreated_at(LocalDate.now());
        newOrder.setUpdated_at(order.getUpdated_at());
        newOrder.setShipAddress(order.getShipAddress());
        newOrder.setCustomerId(order.getCustomerId());
//        newOrder.setStatus(OrderStatus.PENDING.name());
        //tìm cart theo access token
        Cart cart = cartRepository.findCartByAccessToken(access_token);
        Set<OrderDetail> listOrderDetail = new HashSet<>();
        /*for (OrderDetail od: order.getOrderDetails()) {
            Product product = productRepository.findById(od.getId().getProductId()).get();
            System.out.println(product.getId());
            OrderDetailId key = new OrderDetailId();

            key.setProductId(od.getId().getProductId());

            OrderDetail orderDetail = new OrderDetail();

            orderDetail.setOrder(newOrder);
            orderDetail.setProduct(product);

            orderDetail.setId(key);

            orderDetail.setQuantity(od.getQuantity());
            orderDetail.setUnitPrice(od.getUnitPrice());
            orderDetail.setOrder(newOrder);
            listOrderDetail.add(orderDetail);
        }*/

        newOrder.setOrderDetails(listOrderDetail);
        orderRepository.save(newOrder);
        return newOrder;
    }

    @Override
    public ResponseApi findOrder(int id) {

        return null;
    }

    @Override
    public ResponseApi findOrderByCustomerId(int customerId) {
        return new ResponseApi(HttpStatus.OK,"sucess",orderRepository.findOrdersByCustomerId(customerId));
    }


    @Override
    public ResponseApi createOrderDetail(OrderDetail orderDetail) {
        return null;
    }

    @Override
    public ResponseApi deleteOrder(int order) {
        return null;
    }
}
