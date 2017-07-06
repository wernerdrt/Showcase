package org.educama.flightconnection.repository;

import org.educama.flightconnection.model.FlightConnection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The Repository interface for connections.
 */
public interface FlightConnectionRepository extends MongoRepository<FlightConnection, String> {
    Page<FlightConnection> findBySourceAirportIataCodeAndDestinationAirportIataCode(String sourceIataCode, String destinationAirportIataCode, Pageable pageable);

    Page<FlightConnection> findBySourceAirportIataCodeIgnoreCase(String sourceIataCode, Pageable pageable);

    Page<FlightConnection> findBydestinationAirportIataCodeIgnoreCase(String destinationIataCode, Pageable pageable);
}
