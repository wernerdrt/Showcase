import {NgModule} from "@angular/core";
import {FLIGHTS_ROUTES} from "./flights-routing.routes";
import {FlightsCommonModule} from "../flights-common/flights-common.module";
import {RouterModule} from "@angular/router";

@NgModule({
  imports: [
    FlightsCommonModule,
    RouterModule.forChild(FLIGHTS_ROUTES)
  ],
  declarations: []
})
export class FlightsRoutingModule { }
