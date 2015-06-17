package com.yukam.mypam.repository;

import com.yukam.mypam.domain.PricingType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the PricingType entity.
 */
public interface PricingTypeRepository extends JpaRepository<PricingType, Long> {

}
