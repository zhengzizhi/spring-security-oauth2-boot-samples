package com.contoso.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import com.contoso.domain.Flight;

public interface FlightRepository extends CrudRepository<Flight, Long> {

	@Override
	@PreAuthorize("#oauth2.hasScope('read')")
	Iterable<Flight> findAll();

	@Override
	@PreAuthorize("#oauth2.hasScope('read')")
	Optional<Flight> findById(Long aLong);

	@Override
	@PreAuthorize("#oauth2.hasScope('write')")
	<S extends Flight> S save(S entity);

}
