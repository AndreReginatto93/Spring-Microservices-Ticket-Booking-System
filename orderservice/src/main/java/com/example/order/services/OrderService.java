package com.example.order.services;

import com.example.bookingservice.event.BookingEvent;
import com.example.order.entities.OrderEntity;
import com.example.order.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @KafkaListener(topics = "booking", groupId = "order-service")
    public void orderEvent(BookingEvent bookingEvent){
        log.info("Received order event: {}", bookingEvent);

        // Create Order object for database
        OrderEntity orderEntity = createOrder(bookingEvent);
        orderRepository.saveAndFlush(orderEntity);

        // Update Inventory
    }

    private OrderEntity createOrder(BookingEvent bookingEvent) {
        return OrderEntity.builder()
                .customerId(bookingEvent.getUserId())
                .event_id(bookingEvent.getEventId())
                .ticketCount(bookingEvent.getTicketCount())
                .totalPrice(bookingEvent.getTotalPrice())
                .build();
    }
}
