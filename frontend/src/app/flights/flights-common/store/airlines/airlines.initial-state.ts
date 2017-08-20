import {AirlineResource} from "../../api/airlines/airline.resource";
import {AirlineSlice} from "./airlines.slice";
import {SortOrder} from "../../../../shared/enums/sort-order.enum";

export const AIRLINE_SLICE_INITIAL_STATE: AirlineSlice = {
    pageNumber: 0,
    pageSize: 20,
    totalPages: 0,
    sortBy: "country",
    sortOrder: SortOrder.ascending,
    airlineList: new Array<AirlineResource>(),
    loading: false
};
