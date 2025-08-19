package com.example.booking_service.client;

import com.example.booking_service.responses.InventoryResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryServiceClient {

    @Value(value = "${inventory.service.url}")
    private String inventoryServiceUrl;

    public InventoryResponse getInventory(Long eventId){
        final RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(inventoryServiceUrl + "/event/" + eventId, InventoryResponse.class);
    }
}
