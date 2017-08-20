import {NgModule} from "@angular/core";
import {SharedModule} from "../../shared/shared.module";
import {AirlineListPageComponent} from "./container/airline-list-page.component";

@NgModule({
  imports: [
    SharedModule
  ],
  declarations: [
    AirlineListPageComponent
  ],
  exports: [
    AirlineListPageComponent
  ]
})
export class AirlineListModule {
}
