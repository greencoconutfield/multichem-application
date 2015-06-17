package com.yukam.mypam.repository;

import com.yukam.mypam.domain.QuotationItemDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Quotation entity.
 */
public interface QuotationItemDetailRepository extends JpaRepository<QuotationItemDetail, Long> {

}
