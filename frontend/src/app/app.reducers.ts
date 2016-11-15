import {ActionReducer, combineReducers} from "@ngrx/store";
import {
    SHIPMENT_LIST_PAGE_REDUCER,
    SHIPMENT_LIST_SLICE_INITIAL_STATE,
    ShipmentListSlice
} from "./shipment/reducer/shipment-list-page.reducer";

export interface State {
    shipmentListSlice: ShipmentListSlice;
}

export const INITIAL_STATE = {
    shipmentListSlice: SHIPMENT_LIST_SLICE_INITIAL_STATE
};

const reducers = {
    shipmentListSlice: SHIPMENT_LIST_PAGE_REDUCER
};

export const REDUCER: ActionReducer<State> = combineReducers(reducers);
