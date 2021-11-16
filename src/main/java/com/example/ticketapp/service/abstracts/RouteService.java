package com.example.ticketapp.service.abstracts;

import java.util.List;

import com.example.ticketapp.entity.Route;

public interface RouteService {

	Route addRoute(Route route);
	Route updateRoute(Route route);
	void deleteRoute(Long id);
	Route getRouteById(Long id);
	List<Route> getAll();
}
