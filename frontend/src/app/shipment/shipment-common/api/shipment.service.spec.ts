import {ComponentFixture, TestBed, async} from "@angular/core/testing";
import {HttpClientModule} from "@angular/common/http";
import {PactWeb, Matchers} from "@pact-foundation/pact-web";
import {ShipmentService} from "./shipment.service";
import {ShipmentResource} from "./resources/shipment.resource";
import {Party} from "./datastructures/party.datastructure";
import {Cargo} from "./datastructures/cargo.datastructure";
import {RestClientService} from "../../../shared/http/services/rest-client.service";
import {ShipmentServices} from "./datastructures/services.datastructure";
import {ConnectionBackend, Http, HttpModule, RequestMethod, RequestOptions} from "@angular/http";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {HttpHelper} from "../../../shared/http/helper/http.helper";
import {StateObservable, Store, ActionsSubject, ReducerManager, ReducerManagerDispatcher, StoreModule} from "@ngrx/store";
import {ShipmentCommonModule} from "../shipment-common.module";
import {reducers} from "../../../app.reducers";
import {Actions} from "@ngrx/effects";

describe("ShipmentService", () => {

  let provider;
  let options: RequestOptions;

  beforeAll(function (done) {
    provider = new PactWeb({
      consumer: "ui",
      provider: "shipmentService",
      port: 1234,
      host: "localhost",
    });

    // required for slower CI environments
    setTimeout(done, 2000);

    // Required if run with `singleRun: false`
    provider.removeInteractions();
  });

  afterAll(function (done) {
    provider.finalize()
      .then(function () {
        done();
      }, function (err) {
        done.fail(err);
      });
  });

  beforeEach(() => {
    options = new RequestOptions({method: RequestMethod.Post});
    TestBed.configureTestingModule({
      declarations: [
      ],
      imports: [
        HttpModule,
        ShipmentCommonModule,
        StoreModule.forRoot(reducers)
      ],
      providers: [
        ShipmentService,
        RestClientService,
        Http,
        ConnectionBackend,
        { provide: RequestOptions, useValue: {} },
        HttpHelper,
        Store,
        { provide: StateObservable, useValue: {} },
        ActionsSubject,
        ReducerManager,
        ReducerManagerDispatcher,
        Actions
      ]
    });
  });

  afterEach((done) => {
    provider.verify().then(done, e => done.fail(e));
  });

  describe("createShipment()", () => {
    const sender: Party = new Party();
    const receiver: Party = new Party();
    const shipmentCargo: Cargo = new Cargo("this cargo includes pens and other writing articles", 40, 32.5, false, 5);
    const shipmentServices: ShipmentServices = new ShipmentServices(true, false, true, true, true, false, true);
    const expectedShipment:  ShipmentResource = {
      trackingId: "",
      uuidSender: "b423c035-3354-4540-b051-ca6ce8db474d",
      uuidReceiver: "f253fda4-7add-4604-8dae-4c33e0853b83",
      sender: sender,
      receiver: receiver,
      customerTypeEnum: "RECEIVER",
      shipmentCargo: shipmentCargo,
      shipmentServices: shipmentServices
    };

    beforeAll((done) => {
      provider.addInteraction({
        state: "provider accepts a new shipment",
        uponReceiving: "a request to POST a shipment",
        withRequest: {
          method: "POST",
          path: "/shipments",
          body: expectedShipment,
          headers: {
            "Content-Type": "application/json"
          }
        },
        willRespondWith: {
          status: 201,
          body: Matchers.somethingLike({
            "uuidSender" : "b423c035-3354-4540-b051-ca6ce8db474d",
            "uuidReceiver" : "f253fda4-7add-4604-8dae-4c33e0853b83",
            "customerTypeEnum" : "RECEIVER",
            "shipmentCargo" : {
              "numberPackages" : "5",
              "totalWeight" : "40",
              "totalCapacity" : "32.5",
              "cargoDescription" : "this cargo includes pens and other writing articles",
              "dangerousGoods" : false
            },
            "shipmentServices" : {
              "preCarriage" : true,
              "exportInsurance" : false,
              "exportCustomsClearance" : true,
              "flight" : true,
              "importInsurance" : true,
              "importCustomsClearance" : false,
              "onCarriage" : true
            },
            "shipmentFlight" : {
              "flightNumber" : "10243",
              "airline" : "LH",
              "departureAirport" : "FRA",
              "destinationAirport" : "STR",
              "departureTime" : "2015-06-02T21:34:33.616Z",
              "destinationTime" : "2015-06-02T21:34:33.616Z",
              "price" : 100.12
            }
          }),
          headers: {
            "Content-Type": "application/json"
          }
        }
      }).then(done, error => done.fail(error));
    });

    it("should create a Shipment", (done) => {
      const shipmentService: ShipmentService = TestBed.get(ShipmentService);
      console.log("Hallo");
      shipmentService.createShipment(expectedShipment).subscribe(response => {
          expect(response).toEqual(expectedShipment);
        done();
      }, error => {
        done.fail(error);
      });
    });
  });
});
