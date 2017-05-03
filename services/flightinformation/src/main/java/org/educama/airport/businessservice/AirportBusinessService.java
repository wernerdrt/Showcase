package org.educama.airport.businessservice;

import org.educama.airport.datafeed.AirportCsvDeserializer;
import org.educama.airport.model.Airport;
import org.educama.airport.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for Airports.
 */
@Component
public class AirportBusinessService {

    private AirportRepository airportRepository;
    private AirportCsvDeserializer airportCsvDeserializer;

    protected static final int MAX_SUGGESTIONS = 10;

    @Autowired
    public AirportBusinessService(AirportRepository airportRepository, AirportCsvDeserializer airportCsvDeserializer) {
        this.airportRepository = airportRepository;
        this.airportCsvDeserializer = airportCsvDeserializer;
    }

    public Page<Airport> findAllAirports(Pageable pageable) {
        return airportRepository.findAll(pageable);
    }

    public List<Airport> findAirportByIataCode(String iataCode) {
        if (StringUtils.isEmpty(iataCode)) {
            return Collections.emptyList();
        }
        return airportRepository.findByIataCodeIgnoreCase(iataCode);
    }

    public List<Airport> findAirportByIcaoCode(String icaoCode) {
        if (StringUtils.isEmpty(icaoCode)) {
            return Collections.emptyList();
        }
        return airportRepository.findByIcaoCodeIgnoreCase(icaoCode);
    }

    public List<Airport> findAirportsSuggestionsByIataCode(String iataCode) {
        if (StringUtils.isEmpty(iataCode)) {
            return Collections.emptyList();
        }

        List<Airport> suggestions = airportRepository.findByIataCodeStartingWithIgnoreCase(iataCode);

        return suggestions.stream()
                .limit(MAX_SUGGESTIONS)
                .collect(Collectors.toList());
    }

    public List<Airport> findAirportsSuggestionsByIcaoCode(String icaoCode) {
        if (StringUtils.isEmpty(icaoCode)) {
            return Collections.emptyList();
        }

        List<Airport> suggestions = airportRepository.findByIcaoCodeStartingWithIgnoreCase(icaoCode);

        return suggestions.stream()
                .limit(MAX_SUGGESTIONS)
                .collect(Collectors.toList());
    }

    public List<Airport> findAirportsSuggestionsByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return Collections.emptyList();
        }
        List<Airport> suggestions = airportRepository.findByNameStartingWithIgnoreCase(name);
        return suggestions.stream()
                .limit(MAX_SUGGESTIONS)
                .collect(Collectors.toList());
    }

    public List<Airport> findAirportsSuggestionsByCity(String city) {
        if (StringUtils.isEmpty(city)) {
            return Collections.emptyList();
        }
        List<Airport> suggestions = airportRepository.findByCityStartingWithIgnoreCase(city);
        return suggestions.stream()
                .limit(MAX_SUGGESTIONS)
                .collect(Collectors.toList());
    }

    public List<Airport> findAirportsSuggestionsByCountry(String country) {
        if (StringUtils.isEmpty(country)) {
            return Collections.emptyList();
        }
        List<Airport> suggestions = airportRepository.findByCountryStartingWithIgnoreCase(country);
        return suggestions.stream()
                .limit(MAX_SUGGESTIONS)
                .collect(Collectors.toList());
    }

    public void clearAndImportAirports(MultipartFile file) throws IOException {
        List<Airport> airports = airportCsvDeserializer.deserialize(file.getInputStream());

        airportRepository.deleteAll();
        airportRepository.save(airports);
    }

}
