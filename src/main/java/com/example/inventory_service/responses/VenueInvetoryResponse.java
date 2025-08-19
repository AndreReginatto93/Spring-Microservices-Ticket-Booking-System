package com.example.inventory_service.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VenueInvetoryResponse {
    private Long venueid;
    private String venueName;
    private Long venueCapacity;
}
