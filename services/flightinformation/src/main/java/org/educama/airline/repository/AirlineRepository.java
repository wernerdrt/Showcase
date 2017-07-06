package org.educama.airline.repository;

import org.educama.airline.model.Airline;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository interface for airlines.
 */
public interface AirlineRepository extends MongoRepository<Airline, String> {

    List<Airline> findByIataCodeIgnoreCase(String iataCode);

    List<Airline> findByIcaoCodeIgnoreCase(String icaoCode);

    List<Airline> findByNameStartingWithIgnoreCase(String name);

    List<Airline> findByIataCodeStartingWithIgnoreCase(String iataCode);

    List<Airline> findByIcaoCodeStartingWithIgnoreCase(String icaoCode);

    List<Airline> findByCallSignStartingWithIgnoreCase(String callSign);

    List<Airline> findByCountryStartingWithIgnoreCase(String callSign);
}
