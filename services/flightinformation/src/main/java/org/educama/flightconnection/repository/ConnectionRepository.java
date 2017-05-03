package org.educama.flightconnection.repository;

import org.educama.flightconnection.model.Connection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The Repository interface for connections.
 */
public interface ConnectionRepository extends MongoRepository<Connection, String> {
    Page<Connection> findBySourceAirportIataCodeAndDestinationAirportIataCode(String sourceIataCode, String destinationAirportIataCode, Pageable pageable);

    Page<Connection> findBySourceAirportIataCodeIgnoreCase(String sourceIataCode, Pageable pageable);

    Page<Connection> findBydestinationAirportIataCodeIgnoreCase(String destinationIataCode, Pageable pageable);
}
