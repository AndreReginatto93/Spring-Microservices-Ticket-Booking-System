package com.example.inventory_service.controllers;

import com.example.inventory_service.responses.EventInventoryResponse;
import com.example.inventory_service.responses.VenueInvetoryResponse;
import com.example.inventory_service.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventory/events")
    public @ResponseBody List<EventInventoryResponse> inventoryGetAllEvents(){
        return inventoryService.getAllEvents();
    }

    @GetMapping("/inventory/venue/{venueId}")
    public @ResponseBody VenueInvetoryResponse inventoryByVenueId(@PathVariable("venueId") Long venueId){
        return inventoryService.getVenueInformation(venueId);
    }

    @GetMapping("/inventory/event/{eventId}")
    public @ResponseBody EventInventoryResponse getEventInventory(@PathVariable("eventId") Long eventId){
        return inventoryService.getEventInventory(eventId);
    }
}
