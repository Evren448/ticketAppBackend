package com.example.ticketapp.dto;

import java.util.Date;

import javax.persistence.Column;

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
	private int availableCapacity;
    private Date vehicleDate;
}
