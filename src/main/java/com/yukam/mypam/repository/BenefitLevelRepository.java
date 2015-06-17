package com.yukam.mypam.repository;

import com.yukam.mypam.domain.BenefitLevel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the BenefitLevel entity.
 */
public interface BenefitLevelRepository extends JpaRepository<BenefitLevel, Long> {

}
