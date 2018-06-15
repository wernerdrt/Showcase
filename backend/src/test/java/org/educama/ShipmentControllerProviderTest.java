package org.educama;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.loader.PactFilter;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import au.com.dius.pact.provider.junit.State;
import org.educama.customer.api.datastructure.AddressDS;
import org.educama.customer.boundary.CustomerBoundaryService;
import org.educama.customer.model.Address;
import org.educama.customer.model.Customer;
import org.educama.customer.repository.CustomerRepository;
import org.educama.enums.ClientType;
import org.educama.shipment.api.resource.ShipmentResource;
import org.educama.shipment.boundary.ShipmentBoundaryService;
import org.educama.shipment.model.Cargo;
import org.educama.shipment.model.Services;
import org.educama.shipment.model.Shipment;
import org.educama.shipment.repository.ShipmentRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

//import java.util.Collections;

import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Shipment contract testing.
 */
@RunWith(SpringRestPactRunner.class)
@SuppressWarnings("checkstyle:magicnumber")
@PactFolder("C:/Users/Dani/Educama/Showcase/frontend/test/pacts/")
@Provider("shipmentservice")
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {"server.port=8082"}
)
public class ShipmentControllerProviderTest {

    @TestTarget
    public final HttpTarget target = new HttpTarget(8082);
    @MockBean
    private ShipmentBoundaryService shipmentBoundaryService;
    @MockBean
    private CustomerBoundaryService customerBoundaryService;

    private Shipment shipment;

    @Before
    public void setUp (){
        shipment = new Shipment();
        shipment.trackingId = "2dad095a-5f33-4a7e-b068-7c22204994d3";
        shipment.customerTypeEnum = ClientType.SENDER;

        shipment.receiver = new Customer("Daimler AG (Standort Möhringen)", new Address("Epplestraße", "225", "70567", "Stuttgart"));
        shipment.sender = new Customer("Continental AG", new Address("Vahrenwalder Str.", "9", "30165", "Hannover"));
        shipment.shipmentCargo = new Cargo(5, 40.0, 32.5, "this cargo includes pens and other writing articles", null);
        shipment.shipmentServices = new Services(true, false, true, true, true, false, true);
    }

    @State("provider accepts a new shipment")
    public void createShipment() {
        Customer customer1 = new Customer("Daimler AG (Standort Möhringen)", new Address("Epplestraße", "225", "70567", "Stuttgart"));
        customer1.uuid = UUID.fromString("3c808e57-2751-4f1c-8f1c-80772bf540a2");

        Customer customer2 = new Customer("Continental AG", new Address("Vahrenwalder Str.", "9", "30165", "Hannover"));
        customer2.uuid = UUID.fromString("b2443a43-b107-4ea0-91ed-1ccc9369fb59");

        when(customerBoundaryService.findCustomerByUuid(customer1.uuid)).thenReturn(customer1);
        when(customerBoundaryService.findCustomerByUuid(customer2.uuid)).thenReturn(customer2);
        when(shipmentBoundaryService.createShipment(any(Shipment.class))).thenReturn(shipment);
    }

    @State("provider returns a shipment")
    public void returnShipment() {
        ShipmentResource shipmentResource = new ShipmentResource();
        shipmentResource = shipmentResource.fromShipment(shipment);

        when(shipmentBoundaryService.getShipment(shipmentResource.trackingId)).thenReturn(shipmentResource);
        when(shipmentBoundaryService.createShipment(any(Shipment.class))).thenReturn(shipment);
    }
}
