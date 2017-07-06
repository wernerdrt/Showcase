package org.educama.airport.repository;

import org.educama.airport.model.Airport;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository interface for airports.
 */
public interface AirportRepository extends MongoRepository<Airport, String> {

    List<Airport> findByIataCodeIgnoreCase(String iataCode);

    List<Airport> findByNameStartingWithIgnoreCase(String name);

    List<Airport> findByCityStartingWithIgnoreCase(String city);

    List<Airport> findByCountryStartingWithIgnoreCase(String country);

    List<Airport> findByIataCodeStartingWithIgnoreCase(String iataCode);

    List<Airport> findByIcaoCodeStartingWithIgnoreCase(String icaoCode);

    List<Airport> findByIcaoCodeIgnoreCase(String icaoCode);

}
