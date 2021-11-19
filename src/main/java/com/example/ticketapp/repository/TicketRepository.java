package com.example.ticketapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ticketapp.entity.Status;
import com.example.ticketapp.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
	Ticket getTicketById(Long id);
	
	List<Ticket> findUserByUser_Id(Long User_id);
	List<Ticket> findAllByStatus(Status status);
	
	
	List<Ticket> findAllByStatusAndVehicle_VehicleDate(Status status, Date vehicleDate);
	List<Ticket> findAllByStatusAndVehicle_VehicleDateAndRoute_BeginAndRoute_End(Status status, Date vehicleDate, String begin, String end);
	
	
	List<Ticket> findAllByStatusAndVehicle_VehicleDateAndRoute_BeginAndRoute_EndAndUser_Id(Status status, Date vehicleDate, String begin, String end, Long id);

	//findByRoute_BeginAndRoute_End
}
