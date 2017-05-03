package org.educama.airline.businessservice;

import org.educama.airline.datafeed.AirlineCsvDeserializer;
import org.educama.airline.model.Airline;
import org.educama.airline.repository.AirlineRepository;
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
 * Tests the airline business service.
 */
@RunWith(MockitoJUnitRunner.class)
public class AirlineBusinessServiceUnitTest {

    @Mock
    AirlineRepository airlineRepository;

    @Mock
    MultipartFile file;

    @Mock
    InputStream inputStream;
    @Mock
    Pageable pageable;

    @Mock
    AirlineCsvDeserializer airlineCsvDeserializer;

    @Mock
    List<Airline> airlines;

    @InjectMocks
    private AirlineBusinessService cut;

    private final String iata = "ana";


    @Test
    public void findAirlinesByIataCodeRetrievesAirlinesIrrespectiveOfTheCaseOfIATA() {

        // When
        cut.findAirlinesByIataCode(iata);

        // Then
        verify(airlineRepository).findByIataCodeIgnoreCase(iata);
    }

    @Test
    public void findAirlinesSuggestionsByIataCodeRetrievesAirlinsesIrrespectiveOfTheCaseOfIATA() {
        // When
        cut.findAirlinesSuggestionsByIataCode(iata);
        // Then
        verify(airlineRepository).findByIataCodeStartingWithIgnoreCase(iata);
    }

    @Test
    public void findAirlinesSuggestionsByIataCodeReturnsAnEmptyListWhenNoTermSpecified() {
        // When
        List<Airline> suggestions = cut.findAirlinesSuggestionsByIataCode(null);
        // Then
        assertThat(suggestions).isEmpty();

        // When
        suggestions = cut.findAirlinesSuggestionsByIataCode("");
        // Then
        assertThat(suggestions).isEmpty();
    }

    @Test
    public void findAirlinesSuggestionsByCallsignReturnsAnEmptyListWhenNoTermSpecified() {
        // When
        List<Airline> suggestions = cut.findAirlinesSuggestionsByCallSign(null);
        // Then
        assertThat(suggestions).isEmpty();

        // When
        suggestions = cut.findAirlinesSuggestionsByCallSign("");
        // Then
        assertThat(suggestions).isEmpty();
    }

    @Test
    public void findAirlinesSuggestionsByIataCodeTruncatesTheResultSetWhenMoreThanMaximumFound() {
        // Given
        final String term = "iata";

        List<Airline> airlines = new ArrayList<>();
        for (int i = 1; i <= AirlineBusinessService.MAX_SUGGESTIONS + 2; i++) {

            airlines.add(new Airline().withIataCode(term + i));
        }
        when(airlineRepository.findByIataCodeStartingWithIgnoreCase(term)).thenReturn(airlines);

        // When
        List<Airline> suggestions = cut.findAirlinesSuggestionsByIataCode(term);
        // Then
        assertThat(suggestions.size()).isEqualTo(AirlineBusinessService.MAX_SUGGESTIONS);
    }

    @Test
    public void findAirlinesSuggestionsByCallSignTruncatesTheResultSetWhenMoreThanMaximumFound() {
        // Given
        final String term = "callSign";

        List<Airline> airlines = new ArrayList<>();
        for (int i = 1; i <= AirlineBusinessService.MAX_SUGGESTIONS + 2; i++) {

            airlines.add(new Airline().withIataCode(term + i));
        }
        when(airlineRepository.findByCallSignStartingWithIgnoreCase(term)).thenReturn(airlines);

        // When
        List<Airline> suggestions = cut.findAirlinesSuggestionsByCallSign(term);
        // Then
        assertThat(suggestions.size()).isEqualTo(AirlineBusinessService.MAX_SUGGESTIONS);
    }

    @Test
    public void importAirlinesReplacesTheContentOfRepository() throws IOException {
        // Given
        when(file.getInputStream()).thenReturn(inputStream);
        when(airlineCsvDeserializer.deserialize(inputStream)).thenReturn(airlines);

        // When
        cut.clearAndImportAirlines(file);

        // Then
        verify(airlineRepository).deleteAll();
        verify(airlineRepository).save(airlines);

    }

    @Test
    public void findAllAirlinesReturnsAllAirlines() {

        // When
        cut.findAllAirlines(pageable);

        // Then
        verify(airlineRepository).findAll(pageable);
    }

}
