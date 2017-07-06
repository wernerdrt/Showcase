package org.educama.flightconnection.businessservice;

import org.educama.flightconnection.repository.FlightConnectionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

/**
 * Tests the connection business service.
 */
@RunWith(MockitoJUnitRunner.class)
public class FlightFlightConnectionBusinessServiceUnitTest {
    Pageable pageable;
    @Mock
    FlightConnectionRepository flightConnectionRepository;

    @InjectMocks
    FlightConnectionBusinessService cut;

    public void setUp() {
        final int page = 0;
        final int size = 1;
        pageable = new PageRequest(page, size);
    }

    @Test
    public void findFlightConnectionReturnsEmptyListWhenNoSourceAirport() {
        //Given
        final String destinationAirport = "LAX";
        //Then
        assertThat(cut.findFlightConnection(null, destinationAirport, pageable)).isEmpty();
        assertThat(cut.findFlightConnection("", destinationAirport, pageable)).isEmpty();
    }

    @Test
    public void findFlightConnectionReturnsEmptyListWhenNoSourceAndNoDestinationAirport() {
        //Given
        final String sourceAirport = "LAX";
        //Then
        assertThat(cut.findFlightConnection(sourceAirport, null, pageable)).isEmpty();
        assertThat(cut.findFlightConnection(sourceAirport, "", pageable)).isEmpty();
    }

    @Test
    public void findFlightConnectionReturnsListOfConnections() {
        //Given
        final String sourceAirport = "fra";
        final String destinationAirport = "LAX";
        //When
        cut.findFlightConnection(sourceAirport, destinationAirport, pageable);
        //Then
        verify(flightConnectionRepository).findBySourceAirportIataCodeAndDestinationAirportIataCode(sourceAirport.toUpperCase(), destinationAirport, pageable);
    }

    @Test
    public void findFlightConnectionReturnsConnectionsFromSourceAirportWhenOnlySourceAirportSpecified() {
        //Given
        final String source = "LAX";

        cut.findAllConnectionsFromSourceToDestionation(source, null, pageable);
        //Then
        verify(flightConnectionRepository).findBySourceAirportIataCodeIgnoreCase(source.toUpperCase(), pageable);

    }

    @Test
    public void findFlightConnectionReturnsConnectionsToDestinationAirportWhenOnlyDestinationAirportSpecified() {
        //Given
        final String destination = "LAX";
        //When
        cut.findAllConnectionsFromSourceToDestionation(null, destination, pageable);
        //Then
        verify(flightConnectionRepository).findBydestinationAirportIataCodeIgnoreCase(destination.toUpperCase(), pageable);
    }


}
