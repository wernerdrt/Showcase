import {NgModule} from "@angular/core";
import {ShipmentCaptureModule} from "./shipment-capture/shipment-capture.module";
import {ShipmentListModule} from "./shipment-list/shipment-list.module";
import {TaskListModule} from "./task-list/task-list.module";
import {ShipmentCommonModule} from "./shipment-common/shipment-common.module";
import {ShipmentRoutingModule} from "./shipment-routing/shipment-routing.module";

@NgModule({
    imports: [
      ShipmentCaptureModule,
      ShipmentCommonModule,
      ShipmentListModule,
      ShipmentRoutingModule,
      TaskListModule
    ]
})
export class ShipmentModule {
}
