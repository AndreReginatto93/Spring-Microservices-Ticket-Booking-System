package com.example.booking_service.services;

import com.example.booking_service.client.InventoryServiceClient;
import com.example.booking_service.entities.CustomerEntity;
import com.example.booking_service.repositories.CustomerRepository;
import com.example.booking_service.requests.BookingRequest;
import com.example.booking_service.responses.BookingResponse;
import com.example.booking_service.responses.InventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final CustomerRepository customerRepository;
    private final InventoryServiceClient inventoryServiceClient;

    @Autowired
    public BookingService(CustomerRepository customerRepository, InventoryServiceClient inventoryServiceClient) {
        this.customerRepository = customerRepository;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    public BookingResponse createBooking(BookingRequest request){
        // check if user exists
        final CustomerEntity customer = customerRepository.findById(request.getUserId()).orElse(null);
        if (customer == null){
            throw new RuntimeException("User not found");
        }

        // check if there is enough inventory
        InventoryResponse inventoryResponse = inventoryServiceClient.getInventory(request.getEventId());
        System.out.println(inventoryResponse);
        if (inventoryResponse.getCapacity() < request.getTicketCount()){
            throw new RuntimeException("Not enough inventory");
        }
        // get event and venue data

        // create booking

        // send booking to Order Service on a Kafka Topic
        return BookingResponse.builder().build();
    }
}
