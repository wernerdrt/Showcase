package org.educama.airport.controller;


import org.educama.airport.businessservice.AirportBusinessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.verify;

/**
 * Tests the airport REST controller.
 */
@RunWith(MockitoJUnitRunner.class)
public class AirportControllerUnitTest {
    @Mock
    AirportBusinessService airportBusinessService;
    @InjectMocks
    AirportController cut;

    @Test
    public void getAirportByIataCodeOrIcaoCodeReturnsEmptyListWhenTermIsEmpty() {

        assertThat(cut.getAirportByIataCodeOrIcaoCode(null)).isEmpty();
        assertThat(cut.getAirportByIataCodeOrIcaoCode("")).isEmpty();
    }

    @Test
    public void getAirportByIataCodeOrIcaoCodeLooksUpBasedOnIataOrIcaoCode() {
        //Given
        String term = "iata or icao";

        //When
        cut.getAirportByIataCodeOrIcaoCode(term);

        //Then
        verify(airportBusinessService).findAirportByIataCode(term);
        verify(airportBusinessService).findAirportByIcaoCode(term);
    }

    @Test
    public void getAirportSuggestionsReturnsEmptyListWhenTermIsEmpty() {

        assertThat(cut.getAirportSuggestions(null)).isEmpty();
        assertThat(cut.getAirportSuggestions("")).isEmpty();
    }

    @Test
    public void getAirportSuggestionReturnsResultsWhenNameOrCityOrCountryOrIataOrIcaoContainsTheTerm() {
        //Given
        String term = "some term";
        //When
        cut.getAirportSuggestions(term);
        //Then
        verify(airportBusinessService).findAirportsSuggestionsByName(term);
        verify(airportBusinessService).findAirportsSuggestionsByCity(term);
        verify(airportBusinessService).findAirportsSuggestionsByCountry(term);
        verify(airportBusinessService).findAirportsSuggestionsByIataCode(term);
        verify(airportBusinessService).findAirportsSuggestionsByIcaoCode(term);


    }
}
