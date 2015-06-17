package com.yukam.mypam.repository;

import com.yukam.mypam.domain.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Quotation entity.
 */
public interface QuotationRepository extends JpaRepository<Quotation, Long> {

}
