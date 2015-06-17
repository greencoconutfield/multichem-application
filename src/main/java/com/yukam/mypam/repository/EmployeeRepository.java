package com.yukam.mypam.repository;

import com.yukam.mypam.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Quotation entity.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
