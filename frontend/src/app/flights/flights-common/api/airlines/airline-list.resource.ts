import {AirlineResource} from "./airline.resource";
import {AbstractListResource} from "../../../../shared/api/resources/abstract-list.resource";

export class AirlineListResource extends AbstractListResource {
    public content: AirlineResource[];
}
