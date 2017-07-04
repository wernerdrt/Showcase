package org.educama.shipment.process.sentries;

import org.educama.shipment.control.ShipmentControlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class evaluates the sentries in the case diagram.
 */
@Component
public class ShipmentOrderCompletedSentry {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShipmentOrderCompletedSentry.class);

    private ShipmentControlService shipmentControlService;

    @Autowired
    public ShipmentOrderCompletedSentry(ShipmentControlService shipmentBoundaryService) {
        this.shipmentControlService = shipmentBoundaryService;
    }

    public Boolean shipmentOrderCompleted(String trackingId) {
        return shipmentControlService.isShipmentOrderComplete(trackingId);
    }

}
