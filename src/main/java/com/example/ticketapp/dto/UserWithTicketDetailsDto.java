package com.example.ticketapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithTicketDetailsDto {
	private Long userId;
	private Long routeId;
	private Long vehicleId;
	private Long ticketId;
}
