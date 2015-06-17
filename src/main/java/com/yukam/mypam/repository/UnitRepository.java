package com.yukam.mypam.repository;

import com.yukam.mypam.domain.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the BenefitLevel entity.
 */
public interface UnitRepository extends JpaRepository<Unit, Long> {

}
