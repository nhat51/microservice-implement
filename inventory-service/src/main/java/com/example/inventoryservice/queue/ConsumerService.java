package com.example.inventoryservice.queue;

import com.example.inventoryservice.dto.OrderDetailDTO;
import com.example.inventoryservice.dto.OrderDto;
import com.example.inventoryservice.entity.ImportExportHistory;
import com.example.inventoryservice.entity.Product;
import com.example.inventoryservice.enums.Status;
import com.example.inventoryservice.repositoty.ImportExportHistoryRepository;
import com.example.inventoryservice.repositoty.ProductRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.example.inventoryservice.queue.Config.DIRECT_EXCHANGE;
import static com.example.inventoryservice.queue.Config.DIRECT_ROUTING_KEY_ORDER;

@Component
public class ConsumerService {

    @Autowired
    private ImportExportHistoryRepository historyRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Transactional
    public void handleMessage(OrderDto orderDto){
        System.out.println(1);
        if (orderDto.getInventoryStatus().equals(Status.InventoryStatus.PENDING.name())){
            handlePending(orderDto);
            return;
        }
        if (orderDto.getInventoryStatus().equals(Status.InventoryStatus.RETURN.name())){
            System.out.println("Một");
            handleReturn(orderDto);
        }
    }

    @Transactional
    public void handleReturn(OrderDto orderDto){
        Set<OrderDetailDTO> orderDetailDTOs = orderDto.getOrderDetails();
        List<Product> products = new ArrayList<>();
        List<ImportExportHistory> listHistory = new ArrayList<>();
        for (OrderDetailDTO dto : orderDetailDTOs) {
            Optional<Product> product = productRepository.findById(dto.getProductId());
            ImportExportHistory history = new ImportExportHistory();
            history.setType(Status.HistoryType.IMPORT.name());
            history.setOrderId(orderDto.getOrderId());
            history.setCreated_at(LocalDate.now());
            history.setQuantity(dto.getQuantity());
            if (!product.isPresent()) {
               return;
            }
            int quantity = dto.getQuantity();
            int inStock = product.get().getInStock();
            product.get().setInStock(inStock + quantity);
            products.add(product.get());
            listHistory.add(history);
        }
        try{
            historyRepository.saveAll(listHistory);
            productRepository.saveAll(products);
            orderDto.setInventoryStatus(Status.InventoryStatus.RETURNED.name());
            orderDto.setOrderStatus(Status.OrderStatus.DONE.name());
            orderDto.setMessage("Return product success");
            rabbitTemplate.convertAndSend(DIRECT_EXCHANGE,DIRECT_ROUTING_KEY_ORDER,orderDto);
        }catch (Exception e){

        }
    }

    @Transactional
    public void handlePending(OrderDto orderDto){
        System.out.println(2);
        Set<OrderDetailDTO> orderDetailDTOs = orderDto.getOrderDetails();
        List<Product> products = new ArrayList<>();
        List<ImportExportHistory> listHistory = new ArrayList<>();
        for (OrderDetailDTO dto : orderDetailDTOs) {
            ImportExportHistory history = new ImportExportHistory();
            Product product = productRepository.getById(dto.getProductId());
            if (product == null){//lỗi đây
                return;
            }
            history.setOrderId(orderDto.getOrderId());
            history.setType(Status.HistoryType.EXPORT.name());
            history.setProductId(dto.getProductId());
            history.setQuantity(dto.getQuantity());
                int quantity = dto.getQuantity();
                int inStock = product.getInStock();
                if (quantity > inStock) {
                    System.out.println(3);
                    orderDto.setMessage("Product in inventory not enough");
                    orderDto.setInventoryStatus(Status.InventoryStatus.OUT_OF_STOCK.name());
                    orderDto.setOrderStatus(Status.OrderStatus.REJECT.name());

                    rabbitTemplate.convertAndSend(DIRECT_EXCHANGE, DIRECT_ROUTING_KEY_ORDER, orderDto);
                    System.out.println("order dto: " + orderDto.toString());
                    return;
            }
            product.setInStock(inStock - quantity);
            products.add(product);
            listHistory.add(history);
        }
        try {
            productRepository.saveAll(products);
            historyRepository.saveAll(listHistory);
            System.out.println(4);
            orderDto.setInventoryStatus(Status.InventoryStatus.DONE.name());
            orderDto.setOrderStatus(Status.OrderStatus.CONFIRM.name());
            rabbitTemplate.convertAndSend(DIRECT_EXCHANGE, DIRECT_ROUTING_KEY_ORDER, orderDto);
        }catch (Exception e){

        }
    }
}
