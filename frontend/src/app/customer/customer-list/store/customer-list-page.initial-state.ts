import {CustomerResource} from "../../api/resources/customer.resource";

export const CUSTOMER_LIST_SLICE_INITIAL_STATE = {
    pageNumber: 0,
    pageSize: 5,
    totalPages: 0,
    customerList: new Array<CustomerResource>(),
    loading: false
};