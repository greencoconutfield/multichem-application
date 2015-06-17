package com.yukam.mypam.repository;

import com.yukam.mypam.domain.DefaultTaxDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the DefaultTaxDetail entity.
 */
public interface DefaultTaxDetailRepository extends JpaRepository<DefaultTaxDetail, Long> {

}
