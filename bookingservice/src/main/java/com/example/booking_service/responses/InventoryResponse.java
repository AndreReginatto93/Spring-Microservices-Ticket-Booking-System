package com.example.booking_service.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponse {
    private Long eventId;
    private String event;
    private Long capacity;
    private VenueResponse venue;
    private BigDecimal ticketPrice;
}
