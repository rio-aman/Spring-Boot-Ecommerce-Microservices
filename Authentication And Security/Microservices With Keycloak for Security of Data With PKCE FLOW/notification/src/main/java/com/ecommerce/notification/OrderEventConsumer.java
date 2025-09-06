package com.ecommerce.notification;

import com.ecommerce.notification.payload.OrderCreatedEventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Slf4j
@Service
public class OrderEventConsumer {

//    @RabbitListener(queues = "${rabbitmq.queue.name}")
////    public void handleOrderEvent(Map<String, Object> orderEvent){ // before creating the OrderCreatedEventDTO
//
//    public void handleOrderEvent(OrderCreatedEventDTO orderEvent){
//        System.out.println("Received Order Event : " + orderEvent);
//
//        long orderId = orderEvent.getOrderId();
//        OrderStatus status = orderEvent.getStatus();
//
//        // before creating the OrderCreatedEventDTO
////        long orderId = Long.parseLong(orderEvent.get("orderId").toString());
////        String status = orderEvent.get("status").toString();
//
//        System.out.println("Order Id : "+orderId);
//        System.out.println("Order Status : "+status);
//    }
//
//    // Update Database
//    // Send Notification
//    // Send Email
//    // Generate Invoice
//    // Send Seller Notification
//    // these type of things that can we do after the order placed

    @Bean
    private Consumer<OrderCreatedEventDTO> orderCreated(){
        return event -> {
            log.info("Received Order Created Event For Order: {}", event.getOrderId());
            log.info("Received Order Created Event For User Id: {}", event.getUserId());
        };
    }

}
