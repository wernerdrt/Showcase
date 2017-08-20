import {SortOrder} from "../../../../shared/enums/sort-order.enum";
import {AirlineResource} from "../../api/airlines/airline.resource";

export interface AirlineSlice {
    pageNumber: number;
    pageSize: number;
    totalPages: number;
    sortBy: string;
    sortOrder: SortOrder;
    airlineList: AirlineResource[];
    loading: boolean;
}
