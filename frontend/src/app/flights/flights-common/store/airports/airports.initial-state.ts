import {AirportResource} from "../../api/airports/airport.resource";
import {AirportSlice} from "./airports.slice";
import {SortOrder} from "../../../../shared/enums/sort-order.enum";

export const AIRPORT_SLICE_INITIAL_STATE: AirportSlice = {
    pageNumber: 0,
    pageSize: 20,
    totalPages: 0,
    sortBy: "country",
    sortOrder: SortOrder.ascending,
    airportList: new Array<AirportResource>(),
    loading: false
};
