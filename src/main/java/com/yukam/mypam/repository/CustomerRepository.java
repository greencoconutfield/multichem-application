package com.yukam.mypam.repository;

import com.yukam.mypam.domain.Customer;
        import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Customer entity.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
