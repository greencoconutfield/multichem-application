package com.yukam.mypam.repository;

import com.yukam.mypam.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the PricingType entity.
 */
public interface PriceRepository extends JpaRepository<Price, Long> {

}
