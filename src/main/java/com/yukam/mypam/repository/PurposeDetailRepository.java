package com.yukam.mypam.repository;

import com.yukam.mypam.domain.PurposeDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Quotation entity.
 */
public interface PurposeDetailRepository extends JpaRepository<PurposeDetail, Long> {

}
