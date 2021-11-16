package com.example.ticketapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tickets")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
	private Status status; 
    
    @ManyToOne
    @JsonIgnore
    private User user;
    
    @ManyToOne
    @JsonIgnore
    private Vehicle vehicle;
    
    @ManyToOne
    @JsonIgnore
    private Route route;
}
