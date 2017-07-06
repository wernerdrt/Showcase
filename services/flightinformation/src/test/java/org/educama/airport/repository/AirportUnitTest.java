package org.educama.airport.repository;


import org.educama.airport.model.Airport;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Tests the airport model  class.
 */
public class AirportUnitTest {

    Airport firstAirport = new Airport().withName("Los Angeles International Airport")
            .withCity("Los Angeles")
            .withCountry("USA")
            .withIataCode("LAX")
            .withIcaoCode("KLAX");


    Airport secondAirport = new Airport().withName("LA airport")
            .withCity("LA")
            .withCountry("United States")
            .withIataCode("lax")
            .withIcaoCode("foobar");


    @Test
    public void hashcodeReturnSameValueWhenSameIataCodeIrrespectiveOfCase() {
        assertThat(firstAirport.hashCode()).isEqualTo(secondAirport.hashCode());
    }

    @Test
    public void iataCodeIsAlwaysConvertedToUppercase() {
        // Given
        Airport firstAirport = new Airport();
        Airport secondAirport;

        // When
        firstAirport.setIataCode("nsi");
        secondAirport = new Airport().withIataCode("dla");

        // Then
        assertThat(firstAirport.getIataCode()).isEqualTo("NSI");
        assertThat(secondAirport.getIataCode()).isEqualTo("DLA");
    }
}
