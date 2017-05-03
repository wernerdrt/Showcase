package org.educama.airport.datafeed;

import org.educama.airport.model.Airport;
import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Deserializes CSV inputstreams containing airports data into airports Objects.
 */
@Component
public class AirportCsvDeserializer {
    private final String nill = "\\N";
    private final String empty = "";

    public List<Airport> deserialize(InputStream in) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        ICsvBeanReader csvBeanReader = new CsvBeanReader(inputStreamReader, CsvPreference.STANDARD_PREFERENCE);
        final String[] header = {null, "name", "city", "country", "iataCode", "icaoCode", "latitude", "longitude", null, null, null, null, null, null};
        Airport airport = null;
        List<Airport> airports = new ArrayList<>();
        try {

            while ((airport = csvBeanReader.read(Airport.class, header, getCellProcessors())) != null) {
                System.out.println("deserialized " + airport);
                airports.add(airport);
            }
        } finally {
            if (csvBeanReader != null) {
                csvBeanReader.close();
            }
        }
        return airports;
    }

    private CellProcessor[] getCellProcessors() {
        return new CellProcessor[]{
                //@formatter:off
                null,                                  // airport ID
                new Optional(),                        // name
                new Optional(),                        // city
                new Optional(),                        // country
                new Optional(new ParseAirportCode()),  // iataCode
                new Optional(new ParseAirportCode()),  // icaoCode
                new Optional(new ParseDouble()),       // latitude
                new Optional(new ParseDouble()),       // longitude
                null,                                  // altitude
                null,                                  // Timezone
                null,                                  // DST
                null,                                  // Tz database time zone
                null,                                  // Type
                null,                                  // Source
                //@formatter:on
        };

    }

    /**
     * custom CellProcessor for parsing the airports iata or icao codes.
     */
    private class ParseAirportCode extends CellProcessorAdaptor {

        @Override
        public <T> T execute(Object value, CsvContext context) {
            validateInputNotNull(value, context);
            if (value.equals(nill)) {
                return next.execute(empty, context);
            }
            return next.execute(value, context);
        }
    }

}
