package com.yukam.mypam.repository;

import com.yukam.mypam.domain.VisitReportProduct;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Product entity.
 */
public interface VisitReportProductRepository extends JpaRepository<VisitReportProduct, Long> {

}
