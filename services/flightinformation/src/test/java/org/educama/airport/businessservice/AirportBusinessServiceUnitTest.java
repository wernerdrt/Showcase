package org.educama.airport.businessservice;


import org.educama.airport.datafeed.AirportCsvDeserializer;
import org.educama.airport.model.Airport;
import org.educama.airport.repository.AirportRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests the airport business service.
 */
@RunWith(MockitoJUnitRunner.class)
public class AirportBusinessServiceUnitTest {

    @Mock
    AirportRepository airportRepository;

    @Mock
    MultipartFile file;

    @Mock
    InputStream inputStream;
    @Mock
    Pageable pageable;

    @Mock
    AirportCsvDeserializer airportCsvDeserializer;

    @Mock
    List<Airport> airportList;

    @InjectMocks
    private AirportBusinessService cut;

    private final String iata = "ybb";


    @Test
    public void getAirportRetrievesAirportIrrespectiveOfTheCaseOfIATA() {

        // When
        cut.findAirportByIataCode(iata);

        // Then
        verify(airportRepository).findByIataCodeIgnoreCase(iata);
    }

    @Test
    public void getAirportSuggestionsRetrievesAirportsIrrespectiveOfTheCaseOfIATA() {
        // When
        cut.findAirportsSuggestionsByIataCode(iata);
        // Then
        verify(airportRepository).findByIataCodeStartingWithIgnoreCase(iata);
    }

    @Test
    public void getSuggestionsReturnsAnEmptyListWhenNoTermSpecified() {
        // When
        List<Airport> suggestions = cut.findAirportsSuggestionsByIataCode(null);
        // Then
        assertThat(suggestions).isEmpty();

        // When
        suggestions = cut.findAirportsSuggestionsByIataCode("");
        // Then
        assertThat(suggestions).isEmpty();
    }

    @Test
    public void getSuggestionsTruncatesTheResultSetWhenMoreThanMaximumFound() {
        // Given
        final String term = "iata";

        List<Airport> airports = new ArrayList<>();
        for (int i = 1; i <= AirportBusinessService.MAX_SUGGESTIONS + 2; i++) {

            airports.add(new Airport().withIataCode(term + i));
        }
        when(airportRepository.findByIataCodeStartingWithIgnoreCase(term)).thenReturn(airports);

        // When
        List<Airport> suggestions = cut.findAirportsSuggestionsByIataCode(term);
        // Then
        assertThat(suggestions.size()).isEqualTo(AirportBusinessService.MAX_SUGGESTIONS);
    }

    @Test
    public void importAirportReplaceTheContentOfRepository() throws IOException {
        // Given
        when(file.getInputStream()).thenReturn(inputStream);
        when(airportCsvDeserializer.deserialize(inputStream)).thenReturn(airportList);

        // When
        cut.clearAndImportAirports(file);

        // Then
        verify(airportRepository).deleteAll();
        verify(airportRepository).save(airportList);

    }

    @Test
    public void getAllAirportsReturnsAllAirports() {
        //Given

        // When
        cut.findAllAirports(pageable);

        // Then
        verify(airportRepository).findAll(pageable);
    }

}
