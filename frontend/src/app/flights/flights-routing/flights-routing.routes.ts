import {RouterModule, Routes} from "@angular/router";
import {AirportsListPageComponent} from "../airport-list/container/airport-list-page.component";
import {AirlineListPageComponent} from "../airline-list/container/airline-list-page.component";

/*
 * Router configuration for the component fligt
 */
export const FLIGHTS_ROUTES: Routes = [
    {
        path: "airports",
        component: AirportsListPageComponent
    },
    {
        path: "airlines",
        component: AirlineListPageComponent
    }
];
