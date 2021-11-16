package com.example.ticketapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "routes")
public class Route {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "begin")
	private String begin;
	
	@Column(name = "end")
	private String end;
	
	@OneToMany(mappedBy = "route", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ticket> ticketList;
}
