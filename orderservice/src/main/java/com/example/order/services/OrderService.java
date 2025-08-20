package com.example.order.services;

import com.example.booking_service.events.BookingEvent;
import com.example.order.clients.InventoryServiceClient;
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
    private final InventoryServiceClient inventoryServiceClient;

    @Autowired
    public OrderService(OrderRepository orderRepository, InventoryServiceClient inventoryServiceClient) {
        this.orderRepository = orderRepository;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    @KafkaListener(topics = "booking", groupId = "order-service")
    public void orderEvent(BookingEvent bookingEvent){
        log.info("Received order event: {}", bookingEvent);

        // Create Order object for database
        OrderEntity orderEntity = createOrder(bookingEvent);
        orderRepository.saveAndFlush(orderEntity);

        // Update Inventory
        inventoryServiceClient.updateEventCapacity(orderEntity.getEventId(), orderEntity.getTicketCount());
        log.info("Inventory updated for event: {}, less tickets: {}", orderEntity.getEventId(), orderEntity.getTicketCount());
    }

    private OrderEntity createOrder(BookingEvent bookingEvent) {
        return OrderEntity.builder()
                .customerId(bookingEvent.getUserId())
                .eventId(bookingEvent.getEventId())
                .ticketCount(bookingEvent.getTicketCount())
                .totalPrice(bookingEvent.getTotalPrice())
                .build();
    }
}
