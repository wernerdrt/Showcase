package org.educama.flightconnection.repository;


import org.educama.flightconnection.model.FlightConnection;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Tests the connection model.
 */
public class FlightConnectionUnitTest {
    @Test
    public void airlineIataCodeIsAlwaysConvertedToUppercase() {
        // Given
        FlightConnection firstFlightConnection = new FlightConnection();
        FlightConnection secondFlightConnection;

        // When
        firstFlightConnection.setAirlineIataCode("lh");
        secondFlightConnection = new FlightConnection().withAirlineIataCode("ua");

        // Then
        assertThat(firstFlightConnection.getAirlineIataCode()).isEqualTo("LH");
        assertThat(secondFlightConnection.getAirlineIataCode()).isEqualTo("UA");
    }

    @Test
    public void destinationAirportIataCodeIsAlwaysConvertedToUppercase() {
        // Given
        FlightConnection firstFlightConnection = new FlightConnection();
        FlightConnection secondFlightConnection;

        // When
        firstFlightConnection.setDestinationAirportIataCode("nsi");
        secondFlightConnection = new FlightConnection().withDestinationAirportIataCode("dla");

        // Then
        assertThat(firstFlightConnection.getDestinationAirportIataCode()).isEqualTo("NSI");
        assertThat(secondFlightConnection.getDestinationAirportIataCode()).isEqualTo("DLA");
    }

    @Test
    public void sourceAirportIataCodeIsAlwaysConvertedToUppercase() {
        // Given
        FlightConnection firstFlightConnection = new FlightConnection();
        FlightConnection secondFlightConnection;

        // When
        firstFlightConnection.setDestinationAirportIataCode("nsi");
        secondFlightConnection = new FlightConnection().withDestinationAirportIataCode("dla");

        // Then
        assertThat(firstFlightConnection.getDestinationAirportIataCode()).isEqualTo("NSI");
        assertThat(secondFlightConnection.getDestinationAirportIataCode()).isEqualTo("DLA");
    }
}
