package com.yukam.mypam.repository;

import com.yukam.mypam.domain.VisitReportCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Customer entity.
 */
public interface VisitReportCustomerRepository extends JpaRepository<VisitReportCustomer, Long> {

}
