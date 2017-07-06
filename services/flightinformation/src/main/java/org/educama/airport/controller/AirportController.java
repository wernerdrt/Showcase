package org.educama.airport.controller;

import org.educama.airport.businessservice.AirportBusinessService;
import org.educama.airport.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * Rest controller of the airport resources.
 */
@RestController
public class AirportController {


    private AirportBusinessService airportBusinessService;

    @Autowired
    public AirportController(AirportBusinessService airportBusinessService) {
        this.airportBusinessService = airportBusinessService;
    }

    /**
     * Retrieves all airports.
     *
     * @return the airports
     */
    @RequestMapping("/airports")
    public Page<Airport> getAirports(Pageable pageable) {
        return airportBusinessService.findAllAirports(pageable);
    }

    /**
     * Retrieves an airport by its IATA or ICAO code.
     *
     * @param airportCode the IATA Code
     * @return the airport.
     */
    @RequestMapping("/airports/{airportCode}")
    public List<Airport> getAirportByIataCodeOrIcaoCode(@PathVariable String airportCode) {
        Set<Airport> airportSet = new HashSet<Airport>();
        if (StringUtils.isEmpty(airportCode)) {
            return Collections.emptyList();
        }
        airportSet.addAll(airportBusinessService.findAirportByIataCode(airportCode));
        airportSet.addAll(airportBusinessService.findAirportByIcaoCode(airportCode));
        return new ArrayList<>(airportSet);

    }

    /**
     * Retrieves a list of airports where the name, IATA Code or ICAO Code starts with a given term.
     *
     * @param term the term to look up
     * @return the list of matching airports.
     */
    @RequestMapping("/airports/suggestions")
    public List<Airport> getAirportSuggestions(@RequestParam(value = "term") String term) {
        if (StringUtils.isEmpty(term)) {
            return Collections.emptyList();
        }
        return airportBusinessService.findAirportSuggestionsBySearchTerm(term);
    }

    /**
     * Replaces the content of the airports database with the content of the CSV file the data contained in the csv
     * File.
     *
     * @param file the import file containing the airport data
     */
    @RequestMapping(value = "/airports/import/csv", method = RequestMethod.POST)
    @ResponseBody
    public void importAirports(@RequestParam("file") MultipartFile file) throws IOException {
        airportBusinessService.clearAndImportAirports(file);
    }
}
