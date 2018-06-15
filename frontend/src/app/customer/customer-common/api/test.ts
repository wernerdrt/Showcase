import {Action, Store} from "@ngrx/store";
import {TestBed} from "@angular/core/testing";
import {ShipmentService} from "../../../shipment/shipment-common/api/shipment.service";
import {RestClientService} from "../../../shared/http/services/rest-client.service";
import {HttpHelper} from "../../../shared/http/helper/http.helper";
import {HttpModule} from "@angular/http";
import {Address} from "./datastructures/address.datastructure";
import {CustomerResource} from "./resources/customer.resource";
import {Matchers} from "@pact-foundation/pact-web/pact";
import {CustomerService} from "./customer.service";


declare function require(name: string);

const Pact = require("pact-web");

class HttpHelperStub{
  getRestApiBaseUrl(): string{
    return "http://localhost:1234/";
  }
}

class StoreStub{
  dispatch<V extends Action = Action> (action: V){

  }

}
describe("ShipmentService", () => {


    describe("createCustomer()", () => {
      const provider = null;

      afterEach((done) => {
        provider.removeInteractions();
        provider.verify().then(done, e => done.fail(e));

      });

      afterAll(function(done){
        provider.finalize()
          .then(function () { done(); }, function(err){ done.fail(err); });
      });


      beforeEach(function(){
        TestBed.configureTestingModule({
          providers: [
            ShipmentService,
            RestClientService,
            {provide: HttpHelper, useClass: HttpHelperStub},
            {provide: Store, useClass: StoreStub}
          ],
          imports: [HttpModule]
        });

      });
      const address: Address = {
        street: "str",
        streetNo: "1",
        zipCode: "111",
        city: "rt"
      };
      const expectedCustomer: CustomerResource = {
        uuid: "100",
        name: "Dent",
        address: address
      };
      const expectedResponse = {
        "uuid": "54b2a69a-7976-4135-a338-3586ac67895e",
        "name": "John Doe",
        "address": {
          "street": "Normal street",
          "streetNo": "234",
          "zipCode": "10000",
          "city": "Nowhere"
        },
        "_links": {
          "self": {
            "href": "http://localhost:8081/educama/v1/customers/54b2a69a-7976-4135-a338-3586ac67895e"
          }
        }
      };
      beforeAll((done) => {
        provider.addInteraction({
          state: "provider accepts a new customer",
          uponReceiving: "a request to POST a new Customer",
          withRequest: {
            method: "POST",
            path: "/customers",
            body: expectedCustomer,
            headers: {
              "Content-Type": "application/json"
            }
          },
          willRespondWith: {
            status: 201,
            body: Matchers.somethingLike({
              json: expectedResponse
            }),
            headers: {
              "Content-Type": "application/json"
            }
          }
        }).then(done, error => done.fail(error));
      });

      it("should create a customer", (done) => {
        const costumerService: CustomerService = TestBed.get(CustomerService);
        costumerService.createCustomer(expectedCustomer).subscribe(response => {
          expect(response).toEqual(expectedResponse);
          done();
        }, error => {
          done.fail(error);
        });
      });

    });

  });

