import {inject, TestBed} from "@angular/core/testing";
import {ShipmentService} from "./shipment.service";
import {HttpModule} from "@angular/http";
import {RestClientService} from "../../../shared/http/services/rest-client.service";
import {HttpHelper} from "../../../shared/http/helper/http.helper";
import {Action, Store} from "@ngrx/store";
import {ShipmentResource} from "./resources/shipment.resource";
import {Matchers} from "@pact-foundation/pact-web";



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
describe("ShipmentService3", () => {

  let provider;

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

  describe("getShipmentById()", () => {
    const expectedShipment: ShipmentResource = JSON.parse("{\n" +
      "    \"trackingId\": \"2dad095a-5f33-4a7e-b068-7c22204994d3\",\n" +
      "    \"sender\": {\n" +
      "        \"name\": \"Daimler AG (Standort Möhringen)\",\n" +
      "        \"uuid\": \"3c808e57-2751-4f1c-8f1c-80772bf540a2\",\n" +
      "        \"address\": {\n" +
      "            \"street\": \"Epplestraße\",\n" +
      "            \"streetNo\": \"225\",\n" +
      "            \"zipCode\": \"70567\",\n" +
      "            \"city\": \"Stuttgart\"\n" +
      "        }\n" +
      "    },\n" +
      "    \"receiver\": {\n" +
      "        \"name\": \"Continental AG\",\n" +
      "        \"uuid\": \"b2443a43-b107-4ea0-91ed-1ccc9369fb59\",\n" +
      "        \"address\": {\n" +
      "            \"street\": \"Vahrenwalder Str.\",\n" +
      "            \"streetNo\": \"9\",\n" +
      "            \"zipCode\": \"30165\",\n" +
      "            \"city\": \"Hannover\"\n" +
      "        }\n" +
      "    },\n" +
      "    \"shipmentCargo\": {\n" +
      "        \"numberPackages\": 5,\n" +
      "        \"totalWeight\": 40,\n" +
      "        \"totalCapacity\": 32.5,\n" +
      "        \"cargoDescription\": \"this cargo includes pens and other writing articles\",\n" +
      "        \"dangerousGoods\": null\n" +
      "    },\n" +
      "    \"shipmentServices\": {\n" +
      "        \"preCarriage\": true,\n" +
      "        \"exportInsurance\": false,\n" +
      "        \"exportCustomsClearance\": true,\n" +
      "        \"flight\": true,\n" +
      "        \"importInsurance\": true,\n" +
      "        \"importCustomsClearance\": false,\n" +
      "        \"onCarriage\": true\n" +
      "    },\n" +
      "    \"customerTypeEnum\": \"RECEIVER\"\n" +
      "}");
    beforeAll(function(done) {
      provider = Pact({ consumer: "ui3", provider: "shipmentservice", web: true});
      provider.addInteraction({
        uponReceiving: "a request for a shipment with id 2dad095a-5f33-4a7e-b068-7c22204994d3",
        withRequest: {
          method: "GET",
          path: "/shipments/2dad095a-5f33-4a7e-b068-7c22204994d3",
        },
        willRespondWith: {
          status: 200,
          headers: {"Content-Type": "application/json"},
          body: Matchers.somethingLike({
            "trackingId": "2dad095a-5f33-4a7e-b068-7c22204994d3",
            "sender": {
              "name": "Daimler AG (Standort Möhringen)",
              "uuid": "3c808e57-2751-4f1c-8f1c-80772bf540a2",
              "address": {
                "street": "Epplestraße",
                "streetNo": "225",
                "zipCode": "70567",
                "city": "Stuttgart"
              }
            },
            "receiver": {
              "name": "Continental AG",
              "uuid": "b2443a43-b107-4ea0-91ed-1ccc9369fb59",
              "address": {
                "street": "Vahrenwalder Str.",
                "streetNo": "9",
                "zipCode": "30165",
                "city": "Hannover"
              }
            },
            "shipmentCargo": {
              "numberPackages": 5,
              "totalWeight": 40,
              "totalCapacity": 32.5,
              "cargoDescription": "this cargo includes pens and other writing articles",
              "dangerousGoods": null
            },
            "shipmentServices": {
              "preCarriage": true,
              "exportInsurance": false,
              "exportCustomsClearance": true,
              "flight": true,
              "importInsurance": true,
              "importCustomsClearance": false,
              "onCarriage": true
            },
            "customerTypeEnum": "RECEIVER"
          }),
        }
      })
        .then(function () {
          done();
        }, function(err){ done.fail(err); });
    });

    it("should give a shipment", function(done){
      inject([ShipmentService], (service: ShipmentService) => {

        service.findShipmentbyId("2dad095a-5f33-4a7e-b068-7c22204994d3")
          .subscribe(response => {
            expect(response).toEqual(expectedShipment);
            done();
          }, err => done.fail(err), () => {
          });
      })();
    });
  });
});


