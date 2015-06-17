package com.yukam.mypam.repository;

import com.yukam.mypam.domain.VisitReport;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Quotation entity.
 */
public interface VisitReportRepository extends JpaRepository<VisitReport, Long> {

}
