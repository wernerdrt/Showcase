import {NgModule} from "@angular/core";
import {AirportService} from "./flights-common/api/airports/airport.service";
import {AirportListModule} from "./airport-list/airport-list.module";
import {FlightsCommonModule} from "./flights-common/flights-common.module";
import {AirlineListModule} from "./airline-list/airline-list.module";
import {FlightsRoutingModule} from "./flights-routing/flights-routing.module";

@NgModule({
    imports: [
        AirlineListModule,
        AirportListModule,
        FlightsCommonModule,
        FlightsRoutingModule
    ],
    providers: [
      AirportService
    ]
})
export class FlightsModule {
}
