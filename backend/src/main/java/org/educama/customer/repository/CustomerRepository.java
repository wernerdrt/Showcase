package org.educama.customer.repository;

import org.educama.customer.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * JPA Repository for accessing Customer entities.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByUuid(UUID uuid);

    List<Customer> findByAddress(@Param("idAddress") Long idAddress);

    Page<Customer> findByNameStartingWithIgnoreCase(String namme, Pageable pageable);
}
