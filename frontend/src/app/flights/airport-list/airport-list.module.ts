import {NgModule} from "@angular/core";
import {SharedModule} from "../../shared/shared.module";
import {AirportsListPageComponent} from "./container/airport-list-page.component";
import {EffectsModule} from "@ngrx/effects";
import {AirportEffects} from "../flights-common/store/airports/airports.effect";

@NgModule({
  imports: [
    SharedModule,
    EffectsModule.forFeature([
      AirportEffects
    ])
  ],
  declarations: [
    AirportsListPageComponent
  ],
  exports: [
    AirportsListPageComponent
  ]
})
export class AirportListModule {
}
