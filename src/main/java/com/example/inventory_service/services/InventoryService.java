package com.example.inventory_service.services;

import com.example.inventory_service.entities.EventEntity;
import com.example.inventory_service.entities.VenueEntity;
import com.example.inventory_service.repositories.EventRepository;
import com.example.inventory_service.repositories.VenueRepository;
import com.example.inventory_service.responses.EventInventoryResponse;
import com.example.inventory_service.responses.VenueInvetoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {
    private final VenueRepository venueRepository;
    private final EventRepository eventRepository;

    @Autowired
    public InventoryService(VenueRepository venueRepository, EventRepository eventRepository) {
        this.venueRepository = venueRepository;
        this.eventRepository = eventRepository;
    }

    public List<EventInventoryResponse> getAllEvents() {
        final List<EventEntity> events = eventRepository.findAll();

        return events.stream().map(event -> EventInventoryResponse.builder()
                .event(event.getName())
                .capacity(event.getLeftCapacity())
                .venue(event.getVenue())
                .build()).collect(Collectors.toList());
    }

    public VenueInvetoryResponse getVenueInformation(Long venueId){
        final VenueEntity venue = venueRepository.findById(venueId).orElse(null);

        return VenueInvetoryResponse.builder()
                .venueid(venue.getId())
                .venueName(venue.getName())
                .venueCapacity(venue.getTotalCapacity()).build();
    }
}
