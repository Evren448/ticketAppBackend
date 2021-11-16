package com.example.ticketapp.service.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ticketapp.entity.Route;
import com.example.ticketapp.repository.RouteRepository;
import com.example.ticketapp.service.abstracts.RouteService;

@Service
public class RouteManager implements RouteService {
	
	@Autowired
	private RouteRepository routeRepository;

	@Override
	public Route addRoute(Route route) {
		return this.routeRepository.save(route);
	}
	
	@Override
	public Route updateRoute(Route route) {
		Route updateRoute = new Route();
		updateRoute.setBegin(route.getBegin());
		updateRoute.setEnd(route.getEnd());
		updateRoute.setId(route.getId());
		
		return this.routeRepository.save(updateRoute);
		
	}

	@Override
	public void deleteRoute(Long id) {
		this.routeRepository.deleteById(id);
	}

	@Override
	public Route getRouteById(Long id) {
		return this.routeRepository.findById(id).orElse(null);
	}

	@Override
	public List<Route> getAll() {
		return this.routeRepository.findAll();
		
	}

}
