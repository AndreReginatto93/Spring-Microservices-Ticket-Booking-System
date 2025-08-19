package com.example.booking_service.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VenueResponse {
    private Long id;
    private String name;
    private String address;
    private Long totalCapacity;
}
