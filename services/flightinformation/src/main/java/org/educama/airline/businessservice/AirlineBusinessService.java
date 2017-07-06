package org.educama.airline.businessservice;

import org.educama.airline.datafeed.AirlineCsvDeserializer;
import org.educama.airline.model.Airline;
import org.educama.airline.repository.AirlineRepository;
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
 * Service class for airlines.
 */
@Component
public class AirlineBusinessService {

    private AirlineRepository airlineRepository;

    private AirlineCsvDeserializer airlineCsvDeserializer;

    protected static final int MAX_SUGGESTIONS = 10;

    @Autowired
    public AirlineBusinessService(AirlineRepository airlineRepository, AirlineCsvDeserializer airlineCsvDeserializer) {
        this.airlineRepository = airlineRepository;
        this.airlineCsvDeserializer = airlineCsvDeserializer;
    }

    public Page<Airline> findAllAirlines(Pageable pageable) {
        return airlineRepository.findAll(pageable);
    }

    public List<Airline> findAirlinesByIataCode(String iataCode) {
        if (StringUtils.isEmpty(iataCode)) {
            return Collections.emptyList();
        }
        return airlineRepository.findByIataCodeIgnoreCase(iataCode);
    }

    public List<Airline> findAirlinesByIcaoCode(String icaoCode) {
        if (StringUtils.isEmpty(icaoCode)) {
            return Collections.emptyList();
        }
        return airlineRepository.findByIcaoCodeIgnoreCase(icaoCode);
    }

    public List<Airline> findAirlinesSuggestionsByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return Collections.emptyList();
        }
        List<Airline> suggestions = airlineRepository.findByNameStartingWithIgnoreCase(name);

        return suggestions.stream()
                .limit(MAX_SUGGESTIONS)
                .collect(Collectors.toList());
    }

    public List<Airline> findAirlinesSuggestionsByIataCode(String iataCode) {
        if (StringUtils.isEmpty(iataCode)) {
            return Collections.emptyList();
        }

        List<Airline> suggestions = airlineRepository.findByIataCodeStartingWithIgnoreCase(iataCode);

        return suggestions.stream()
                .limit(MAX_SUGGESTIONS)
                .collect(Collectors.toList());
    }

    public List<Airline> findAirlinesSuggestionsByIcaoCode(String icaoCode) {
        if (StringUtils.isEmpty(icaoCode)) {
            return Collections.emptyList();
        }

        List<Airline> suggestions = airlineRepository.findByIcaoCodeStartingWithIgnoreCase(icaoCode);

        return suggestions.stream()
                .limit(MAX_SUGGESTIONS)
                .collect(Collectors.toList());
    }


    public List<Airline> findAirlinesSuggestionsByCallSign(String callSign) {
        if (StringUtils.isEmpty(callSign)) {
            return Collections.emptyList();
        }

        List<Airline> suggestions = airlineRepository.findByCallSignStartingWithIgnoreCase(callSign);

        return suggestions.stream()
                .limit(MAX_SUGGESTIONS)
                .collect(Collectors.toList());
    }

    public List<Airline> findAirlinesSuggestionsByCountry(String country) {
        if (StringUtils.isEmpty(country)) {
            return Collections.emptyList();
        }

        List<Airline> suggestions = airlineRepository.findByCountryStartingWithIgnoreCase(country);

        return suggestions.stream()
                .limit(MAX_SUGGESTIONS)
                .collect(Collectors.toList());
    }

    public void clearAndImportAirlines(MultipartFile file) throws IOException {
        List<Airline> airlines = airlineCsvDeserializer.deserialize(file.getInputStream());

        airlineRepository.deleteAll();
        airlineRepository.save(airlines);
    }

}
