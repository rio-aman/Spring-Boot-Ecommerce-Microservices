package com.ecommerce.order.service;

import com.ecommerce.order.dto.OrderCreatedEventDTO;
import com.ecommerce.order.dto.OrderItemDto;
import com.ecommerce.order.dto.OrderResponse;
import com.ecommerce.order.dto.UserResponse;
import com.ecommerce.order.httpinterface.UserServiceClient;
import com.ecommerce.order.model.*;
import com.ecommerce.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartService cartService;
    private final UserServiceClient userServiceClient;
    private final OrderRepository orderRepository;

    // using the spring cloud stream (stream bridge)
    private final StreamBridge streamBridge;

    public Optional<OrderResponse> createOrder(String userId) {
        //Validate the cart items
        List<CartItem> cartItems = cartService.getCart(userId);
        if(cartItems.isEmpty()){
            return Optional.empty();
        }

        //Validate for the user
        UserResponse userResponse = userServiceClient.getUserDetails(userId);
        if(userResponse == null){
            return Optional.empty();
        }

        //Calculate total price
        BigDecimal totalPrice = cartItems.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        //Create order
        Order order = new Order();
        order.setUserId(userId);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setTotalAmount(totalPrice);
        List<OrderItem> orderItems = cartItems.stream()
                .map(item -> new OrderItem(
                        null,
                        item.getProductId(),
                        item.getQuantity(),
                        item.getPrice(),
                        order
                ))
                .toList();
        order.setItems(orderItems);
        Order saveOrder = orderRepository.save(order);

        //Clear the cart
        cartService.clearCart(userId);

        // Publish order created event
        OrderCreatedEventDTO event = new OrderCreatedEventDTO(
                saveOrder.getId(),
                saveOrder.getUserId(),
                saveOrder.getStatus(),
                mapToOrderItemDtos(saveOrder.getItems()),
                saveOrder.getTotalAmount(),
                saveOrder.getCreatedAt()
        );

        streamBridge.send("createOrder-out-0",event);
        return Optional.of(mapToOrderResponse(saveOrder));
    }

    private List<OrderItemDto> mapToOrderItemDtos(List<OrderItem> items){
        return items.stream()
                .map(item -> new OrderItemDto(
                        item.getId(),
                        item.getProductId(),
                        item.getQuantity(),
                        item.getPrice(),
                        item.getPrice().multiply(new BigDecimal(item.getQuantity()))
                )).collect(Collectors.toList());
    }

    private OrderResponse mapToOrderResponse(Order order) {
        // this method is for converting the type of Order Object into Order Response
        return new OrderResponse(
                order.getId(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getItems().stream()
                        .map(orderItem -> new OrderItemDto(
                                orderItem.getId(),
                                orderItem.getProductId(),
                                orderItem.getQuantity(),
                                orderItem.getPrice(),
                                orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity()))
                        ))
                        .toList(),
                order.getCreatedAt()
        );
    }
}