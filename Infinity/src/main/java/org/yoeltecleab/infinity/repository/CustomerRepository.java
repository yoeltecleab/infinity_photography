package org.yoeltecleab.infinity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yoeltecleab.infinity.model.Customer;

/**
 * This interface implements the JpaRepository for the basic crud operations
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}