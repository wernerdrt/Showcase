package org.educama.shipment.process.sentries;

import org.educama.shipment.model.Shipment;
import org.educama.shipment.repository.ShipmentRepository;
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

    @Autowired
    ShipmentRepository shipmentRepository;

    public Boolean shipmentOrderCompleted(String trackingId) {
        LOGGER.debug("evaluating shipment status with id: '" + trackingId + "'");
        Shipment shipment = shipmentRepository.findOneBytrackingId(trackingId);

        boolean isShipmentComplete = (shipment != null
                && shipment.shipmentCargo.cargoDescription != null
                && shipment.shipmentCargo.numberPackages != null
                && shipment.shipmentCargo.totalCapacity != null
                && shipment.shipmentCargo.totalWeight != null);

        LOGGER.debug("Shipment is complete: " + isShipmentComplete);
        return isShipmentComplete;
    }

}
