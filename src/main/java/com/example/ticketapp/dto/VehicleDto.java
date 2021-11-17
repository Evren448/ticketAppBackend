package com.example.ticketapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {
	private Long id;
	private String name;
	private int seatingCapacity;
	private Long routeId;
	private String beginPoint;
	private String endPoint;
}
