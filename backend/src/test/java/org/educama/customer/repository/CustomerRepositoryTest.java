package org.educama.customer.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.UUID;

import javax.validation.ConstraintViolationException;

import org.educama.customer.model.Address;
import org.educama.customer.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Tests for {@link CustomerRepository}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SuppressWarnings("checkstyle:magicnumber")
public class CustomerRepositoryTest {

    private static final Pageable PAGEABLE = new PageRequest(0, 10);

    @Autowired
    private CustomerRepository customerRepository;

    private UUID uuidCustomer1;
    private UUID uuidCustomer2;
    private UUID uuidCustomer3;

    @Before
    public void createTestData() {
        customerRepository.deleteAll();

        Address address = new Address("Königstraße", "38", "70013", "Stuttgart");

        Customer customer = new Customer("Marty Maredo", address);
        this.uuidCustomer1 = customerRepository.save(customer).uuid;

        customer = new Customer("Marge Maredo", address);
        this.uuidCustomer2 = customerRepository.save(customer).uuid;

        customer = new Customer("Mat Maredo", address);
        this.uuidCustomer3 = customerRepository.save(customer).uuid;
    }

    @Test
    public void checkTestDataAvailable() {
        List<Customer> allCustomers = customerRepository.findAll();
        assertEquals(3, allCustomers.size());
    }

    @Test
    public void shouldNotCreateCustomerWhenIncomplete() {
        Customer savedCustomer = null;
        try {
            Customer customer = new Customer("Marty", null);
            savedCustomer = customerRepository.save(customer);
            fail();
        } catch (ConstraintViolationException e) {
            assertTrue(savedCustomer == null);
        }
    }

    @Test
    public void shouldDeleteCustomer() {
        Customer customer = customerRepository.findByUuid(this.uuidCustomer1);
        customerRepository.delete(customer);
        assertEquals(2, customerRepository.count());
    }

    @Test
    public void shouldPageThroughCustomers() {
        String[] names = new String[] {"Marty Maredo", "Marge Maredo", "Mat Maredo"};
        int loopCount = 0;
        Pageable pageable = new PageRequest(0 /* first */, 2 /* one at a time */);
        Page<Customer> page = null;

        do {
            page = customerRepository.findAll(pageable);
            loopCount++;
            assertEquals(3, page.getTotalElements());
            if (loopCount != 2) {
                assertEquals(2, page.getNumberOfElements());
            } else {
                assertEquals(1, page.getNumberOfElements());
            }

            Customer customer = page.getContent().get(0);
            assertEquals(names[pageable.getPageNumber() * 2], customer.name);
            pageable = pageable.next();
        } while (pageable.getPageNumber() * pageable.getPageSize() < page.getTotalElements());
        assertEquals(2, loopCount);
    }

    @Test
    public void shouldFindCustomersWithEmptyPageable() {
        Page<Customer> page = customerRepository.findAll((Pageable) null);
        assertEquals(3, page.getTotalElements());
        assertEquals(3, page.getContent().size());
    }

    @Test
    public void shouldUpdateCustomer() {
        Customer customer = customerRepository.findByUuid(this.uuidCustomer1);
        customer.name = "Marty Montana";
        Customer updatedCustomer = customerRepository.save(customer);
        assertEquals("Marty Montana", updatedCustomer.name);
    }

}
