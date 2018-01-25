import {LeftSideBarModule} from "./caseUI-leftSideBar/leftSideBar.module";
import {NgModule} from "@angular/core";
import {CaseUiComponent} from "./caseUi.component";
import {CaseUIShipmentDetailModule} from "./caseUI-shipmentDetail/caseUI-shipmentDetail.module";
import {ROUTING} from "../../app.routes";
import {NavigationModule} from "../../navigation/navigation.module";
import {SharedModule} from "primeng/primeng";

@NgModule({
  imports: [
    LeftSideBarModule,
    CaseUIShipmentDetailModule,
    NavigationModule,
    ROUTING
  ],
  declarations: [
    CaseUiComponent
  ],
  exports: [
    CaseUiComponent
  ]
})
export class CaseUIModule {
}
