package com.contoso.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.StringUtils;

import com.contoso.domain.Flight;
import com.contoso.repository.FlightRepository;

@RestController
@RequestMapping("/flights")
public class FlightController {

	@Autowired
	private FlightRepository flightRepository;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Flight>> getFlight(@PathVariable("id") Long id) {

		Optional<Flight> flight = flightRepository.findById(id);

		return new ResponseEntity<>(flight, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Flight>> getAllFlight() {

		List<Flight> flights = (List<Flight>) flightRepository.findAll();

		return new ResponseEntity<>(flights, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {

		if (flight.getTraveler() == null || StringUtils.isEmpty(flight.getAirline())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Flight savedFlight = flightRepository.save(flight);
		return new ResponseEntity<>(savedFlight, HttpStatus.CREATED);
	}

}
