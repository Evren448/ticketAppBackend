package com.example.ticketapp.dto;


import com.example.ticketapp.entity.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
	private Long id;
	private Status status; 
    private Long userId;
    private Long vehicleId;
    private Long routeId;
    
    private String ticketOwner;
    private String routeStart;
    private String routeEnd;
}
