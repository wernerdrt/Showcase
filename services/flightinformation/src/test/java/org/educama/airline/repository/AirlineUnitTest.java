package org.educama.airline.repository;


import org.educama.airline.model.Airline;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Tests the airline model.
 */
public class AirlineUnitTest {

    Airline firstAirline = new Airline().withName("Air Canada")
            .withAlias("")
            .withIataCode("AC")
            .withIcaoCode("ACA")
            .withCallSign("AIR CANADA")
            .withCountry("Canada");

    Airline secondAirline = new Airline().withName("lorem ipsum")
            .withAlias("")
            .withIataCode("ac")
            .withIcaoCode("afl")
            .withCallSign("foobar")
            .withCountry("canada");


    @Test
    public void hashcodeReturnSameValueWhenSameIataCodeIrrespectiveOfCase() {
        assertThat(firstAirline.hashCode()).isEqualTo(secondAirline.hashCode());
    }

    @Test
    public void iataCodeIsAlwaysConvertedToUppercase() {
        //Given
        Airline firstAirline = new Airline();
        Airline secondAirline;

        //When
        firstAirline.setIataCode("lh");
        secondAirline = new Airline().withIataCode("su");

        //Then
        assertThat(firstAirline.getIataCode()).isEqualTo("LH");
        assertThat(secondAirline.getIataCode()).isEqualTo("SU");
    }
}
