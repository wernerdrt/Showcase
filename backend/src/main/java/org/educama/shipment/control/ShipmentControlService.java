package org.educama.shipment.control;

import org.educama.shipment.model.Shipment;
import org.educama.shipment.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Control service for shipments.
 */
@Component
public class ShipmentControlService {

    private ShipmentRepository shipmentRepository;

    @Autowired
    public ShipmentControlService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public boolean isShipmentOrderComplete(String trackingId) {
        Shipment shipment = shipmentRepository.findOneBytrackingId(trackingId);

        return (shipment != null
                && shipment.shipmentCargo.cargoDescription != null
                && shipment.shipmentCargo.numberPackages != null
                && shipment.shipmentCargo.totalCapacity != null
                && shipment.shipmentCargo.totalWeight != null);
    }
}
