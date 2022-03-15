package com.example.orderservice.service.cart;

import com.example.orderservice.entity.*;
import com.example.orderservice.entityDTO.OrderDto;
import com.example.orderservice.repository.CartItemRepository;
import com.example.orderservice.repository.CartRepository;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.response.ResponseApi;
import com.example.orderservice.status.Status;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.orderservice.queue.Config.*;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;



    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Override
    public ResponseApi addToCart(String userId, CartItem cartItem) {
        Cart exist = cartRepository.findCartByUserId(userId);
        //Kiểm tra người dùng có giỏ hàng hay chưa
        if (exist != null) {
            Set<CartItem> listCartItem = exist.getItems();
            for (CartItem item : listCartItem) {
                /*
                kiểm tra sản phẩm có tồn tại trong giỏ hàng hay không
                * nếu có thì tăng quantity và lưu lại
                *
                * */
                if (item.getProductId()==cartItem.getProductId()) {
                    item.setQuantity(item.getQuantity() + 1);
                    exist.setTotalMoney();
                    return new ResponseApi(HttpStatus.OK,"success",cartRepository.save(exist));
                }
            }
            cartItem.setCartId(exist.getId());
            listCartItem.add(cartItem);
            exist.setItems(listCartItem);
            exist.setTotalMoney();
            return new ResponseApi(HttpStatus.OK,"success",cartRepository.save(exist));
        }
        Cart newCart = new Cart();
        Cart saved = cartRepository.save(newCart);
        newCart.setUserId(userId);
        Set<CartItem> newCartItem = new HashSet<>();
        newCartItem.add(cartItem);
        cartItem.setCartId(saved.getId());
        saved.setItems(newCartItem);
        saved.setTotalMoney();
        return new ResponseApi(HttpStatus.OK,"success",cartRepository.save(newCart));
    }

    @Override
    public ResponseApi update(String userId,CartItem cartItem) {
        Cart exist = cartRepository.findCartByUserId(userId);
        Set<CartItem> cartItemList = exist.getItems();
        for (CartItem item : cartItemList) {
            if (item.getProductId() == cartItem.getProductId()){
                item.setQuantity(cartItem.getQuantity());
                cartItemRepository.save(item);
            }
        }
        return new ResponseApi(HttpStatus.OK,"updated",exist);
    }

    @Override
    public ResponseApi remove(String userId,int productId) {
        Cart exist = cartRepository.findCartByUserId(userId);
        Set<CartItem> itemSet = exist.getItems();
        for (CartItem item: itemSet) {
            if (item.getProductId() == productId){
                itemSet.remove(item);
                cartItemRepository.delete(item);
            }
        }
        exist.setTotalMoney();
        return new ResponseApi(HttpStatus.OK,"success",exist);
    }

    @Override
    public ResponseApi clear(String userId) {
        //tìm cái cart theo access token
        Cart exist = cartRepository.findCartByUserId(userId);
        Set<CartItem> itemSet = exist.getItems();
        itemSet.clear();
        List<CartItem> cartItemList = cartItemRepository.findCartItemsByCart_Id(exist.getId());
        cartItemRepository.deleteAll(cartItemList);
        exist.setTotalMoney();
        return new ResponseApi(HttpStatus.OK,"success",exist);
    }

    @Override
    public ResponseApi getCart(String userId) {
        Cart cart = cartRepository.findCartByUserId(userId);
        cart.setTotalMoney();
        return new ResponseApi(HttpStatus.OK,"success",cart);
    }

    /*
    * Chuyển từ cart sang order
    * */
    @Override
    public ResponseApi prepareOrder(String userId, Order order) {
        //tìm giỏ hàng theo access token
        Cart cart = cartRepository.findCartByUserId(userId);
        if (cart.getItems().size() == 0){
            return new ResponseApi(HttpStatus.BAD_REQUEST,"Bad request","Cart is empty");
        }
        // generate id, tính tổng tiền, set ngày tháng, set các thông tin
        Set<OrderDetail> orderDetails = new HashSet<>();
        // chuyển từ cart item sang order detail
        for (CartItem cartItem : cart.getItems()) {
            OrderDetailId key = new OrderDetailId();

            key.setProductId(cartItem.getProductId());
            OrderDetail orderDetail = new OrderDetail();

            orderDetail.setId(key);
            orderDetail.setOrder(order);

            orderDetail.setProduct_name(cartItem.getProductName());
            orderDetail.setProductId(cartItem.getProductId());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setUnitPrice(cartItem.getUnitPrice());
            orderDetail.setProductId(cartItem.getProductId());
            orderDetails.add(orderDetail);
        }
        order.setStatus(Status.OrderStatus.PENDING.name());
        order.setPayment_status(Status.PaymentStatus.PENDING.name());
        order.setInventory_status(Status.InventoryStatus.PENDING.name());
        order.setCreated_at(LocalDate.now());
        order.setTotalPrice(cart.getTotalPrice());
        order.setOrderDetails(orderDetails);
        orderRepository.save(order);
        clear(userId);
        cart.setTotalMoney();
        cartRepository.save(cart);
        try{
            OrderDto orderDto = new OrderDto(order);
            rabbitTemplate.convertAndSend(DIRECT_EXCHANGE,DIRECT_ROUTING_KEY_PAY,orderDto);
            rabbitTemplate.convertAndSend(DIRECT_EXCHANGE,DIRECT_ROUTING_KEY_INVENT,orderDto);
        }catch (Exception e){

        }
        return new ResponseApi(HttpStatus.CREATED,"Success",order);
    }

    @Override
    public double calculateTotalPrice() {
        return 0;
    }
}
