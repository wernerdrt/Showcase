package org.educama;

import org.educama.shipment.cmmn.sentries.ShipmentOrderCompletedSentry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class provides all beans or mocks for the process tests. They will be
 * loaded in class {@link ShipmentCaseModelTest}
 */
@Configuration
public class BeanTestConfiguration {

    @Bean
    public ShipmentOrderCompletedSentry shipmentOrderCompletedSentry() {
        return new ShipmentOrderCompletedSentry();
    }

}
