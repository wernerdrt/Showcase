import {Address} from "../../../customer/api/datastructures/address.datastructure";
import {Cargo} from "../../api/datastructures/cargo.datastructure";
import {ShipmentServices} from "../../api/datastructures/services.datastructure";
import {Party} from "../../api/datastructures/party.datastructure";
export class SaveShipmentEvent {
    trackingId?: string;
    uuidSender: string;
    uuidReceiver: string;
    shipmentCargo: Cargo;
    shipmentServices: ShipmentServices;
    customerTypeEnum: string;

    constructor(uuidSender: string, uuidReceiver: string,
                shipmentCargo: Cargo, shipmentServices: ShipmentServices, customerTypeEnum: string, trackingId: string) {
        // this.trackingId = trackingId;
        this.uuidSender = uuidSender;
        this.uuidReceiver = uuidReceiver;
        this.shipmentCargo = shipmentCargo;
        this.shipmentServices = shipmentServices;
        this.customerTypeEnum = customerTypeEnum;
        this.trackingId = trackingId;
    }
}
