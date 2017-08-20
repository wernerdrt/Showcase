import {Action} from "@ngrx/store";
import {AirlineListResource} from "../../api/airlines/airline-list.resource";
import {SortOrder} from "../../../../shared/enums/sort-order.enum";

// Initialize Actions
export const INITIALIZE_AIRLINE_SLICE = "INITIALIZE_AIRLINE_SLICE";

// Customer List Actions
export const CHANGE_AIRLINES_PAGE = "CHANGE_AIRLINES_PAGE";
export const CHANGE_AIRLINES_SORTING = "CHANGE_AIRLINES_SORTING";

// Customer API Actions
export const REQUEST_AIRLINES = "REQUEST_AIRLINES";
export const REQUEST_AIRLINES_SUCCESSFUL = "REQUEST_AIRLINES_SUCCESSFUL";
export const REQUEST_AIRLINES_FAILED = "REQUEST_AIRLINES_FAILED";

export class InitializeAirlineSliceAction implements Action {
    type = INITIALIZE_AIRLINE_SLICE;

    constructor() {
    }
}

export class ChangeAirlinesPageAction implements Action {
    type = CHANGE_AIRLINES_PAGE;

    constructor(public payload: { pageNumber: number, pageSize: number }) {
    }
}

export class ChangeAirlinesSortingAction implements Action {
    type = CHANGE_AIRLINES_SORTING;

    constructor(public payload: { sortBy: string, sortOrder: SortOrder }) {
    }
}

export class RequestAirlinesAction implements Action {
    type = REQUEST_AIRLINES;

    constructor() {
    }
}

export class RequestAirlinesSuccessfulAction implements Action {
    type = REQUEST_AIRLINES_SUCCESSFUL;

    constructor(public payload: AirlineListResource) {
    }
}

export class RequestAirlinesFailedAction implements Action {
    type = REQUEST_AIRLINES_FAILED;

    constructor() {
    }
}
