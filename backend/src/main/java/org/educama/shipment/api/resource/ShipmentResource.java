package org.educama.shipment.api.resource;

import org.educama.shipment.model.Shipment;

public class ShipmentResource {

    public long id;
    public String trackingId;
    public String senderAddress;
    public String receiverAddress;
    public String customer;

    /**
     * Create a API-Model (Resource) instance from the internal data model.
     *
     * @param shipmentModel instance of the internal-data model
     * @return a converted ShipmentResource
     */
    public ShipmentResource fromShipment(Shipment shipmentModel) {
        this.senderAddress = shipmentModel.senderAddress;
        this.receiverAddress = shipmentModel.receiverAddress;
        this.id = shipmentModel.getId();
        this.trackingId = shipmentModel.trackingId;
        this.customer = shipmentModel.customer;

        return this;
    }

    /**
     * Convert this instance of API-Model (Resource) to the internal data model.
     *
     * @return the converted instance
     */
    public Shipment toShipment() {
        Shipment toConvert = new Shipment();
        toConvert.senderAddress = senderAddress;
        toConvert.receiverAddress = receiverAddress;
        toConvert.trackingId = trackingId;
        toConvert.customer = customer;

        return toConvert;
    }
}