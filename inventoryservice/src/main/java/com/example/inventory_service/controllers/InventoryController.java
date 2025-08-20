package com.example.inventory_service.controllers;

import com.example.inventory_service.responses.EventInventoryResponse;
import com.example.inventory_service.responses.VenueInvetoryResponse;
import com.example.inventory_service.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/events")
    public @ResponseBody List<EventInventoryResponse> inventoryGetAllEvents(){
        return inventoryService.getAllEvents();
    }

    @GetMapping("/venue/{venueId}")
    public @ResponseBody VenueInvetoryResponse inventoryByVenueId(@PathVariable("venueId") Long venueId){
        return inventoryService.getVenueInformation(venueId);
    }

    @GetMapping("/event/{eventId}")
    public @ResponseBody EventInventoryResponse getEventInventory(@PathVariable("eventId") Long eventId){
        return inventoryService.getEventInventory(eventId);
    }

    @PutMapping("/event/{eventId}/capacity/{capacity}")
    public ResponseEntity<Void> updateEventCapacity(@PathVariable("eventId") Long eventId,
                                                    @PathVariable("capacity") Long ticketBooked){
        inventoryService.updateEventCapacity(eventId, ticketBooked);
        return ResponseEntity.ok().build();
    }
}
