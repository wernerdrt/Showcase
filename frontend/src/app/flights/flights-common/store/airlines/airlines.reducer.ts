import {Action, ActionReducer} from "@ngrx/store";
import {AirlineSlice} from "./airlines.slice";
import {AIRLINE_SLICE_INITIAL_STATE} from "./airlines.initial-state";
import * as actions from "./airlines.actions";

export function airlineReducer(state: AirlineSlice = AIRLINE_SLICE_INITIAL_STATE, action: Action) {
    switch (action.type) {

        case actions.CHANGE_AIRLINES_PAGE:
            const changeAirlinePageAction = action as actions.ChangeAirlinesPageAction;
            return Object.assign({}, state, {
                pageNumber: changeAirlinePageAction.payload.pageNumber,
                pageSize: changeAirlinePageAction.payload.pageSize
            });

        case actions.CHANGE_AIRLINES_SORTING:
            const changeAirlinesSortingAction = action as actions.ChangeAirlinesSortingAction;
            return Object.assign({}, state, {
                sortBy: changeAirlinesSortingAction.payload.sortBy,
                sortOrder: changeAirlinesSortingAction.payload.sortOrder
            });

        case actions.REQUEST_AIRLINES:
            return Object.assign({}, state, {
                loading: true
            });

        case actions.REQUEST_AIRLINES_SUCCESSFUL:
            const requestAirlinesSuccessfulAction = action as actions.RequestAirlinesSuccessfulAction;
            return Object.assign({}, state, {
                airlineList: requestAirlinesSuccessfulAction.payload.content,
                totalPages: requestAirlinesSuccessfulAction.payload.totalPages,
                loading: false
            });

        case actions.REQUEST_AIRLINES_FAILED:
            return Object.assign({}, state, {
                loading: false
            });

        default:
            return state;
    }
}

export const AIRLINE_REDUCER: ActionReducer<AirlineSlice> = airlineReducer;
