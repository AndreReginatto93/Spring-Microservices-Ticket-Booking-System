package com.example.order.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryServiceClient {
    @Value(value = "${inventory.service.url}")
    private String inventoryServiceUrl;

    public ResponseEntity<Void> updateEventCapacity(Long eventId, Long ticketBooked){
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(inventoryServiceUrl + "/event/" + eventId + "/capacity/" + ticketBooked, null);
        return ResponseEntity.ok().build();
    }
}
