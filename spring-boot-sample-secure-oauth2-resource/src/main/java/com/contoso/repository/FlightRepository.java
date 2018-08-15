package com.contoso.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.contoso.domain.Flight;


public interface FlightRepository extends CrudRepository<Flight, Long> {

	@Override
	Iterable<Flight> findAll();

	@Override
	Optional<Flight> findById(Long aLong);

	@Override
	<S extends Flight> S save(S entity);

}
