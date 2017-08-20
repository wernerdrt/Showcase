import {NgModule} from "@angular/core";
import {SharedModule} from "../../shared/shared.module";
import {EffectsModule} from "@ngrx/effects";
import {AirportService} from "./api/airports/airport.service";
import {AirportEffects} from "./store/airports/airports.effect";
import {AirlineEffects} from "./store/airlines/airlines.effects";
import {AirlineService} from "./api/airlines/airline.service";

@NgModule({
  imports: [
    EffectsModule.forFeature([
      AirlineEffects,
      AirportEffects
    ]),
    SharedModule
  ],
  providers: [
    AirlineService,
    AirportService
  ]
})
export class FlightsCommonModule {
}
