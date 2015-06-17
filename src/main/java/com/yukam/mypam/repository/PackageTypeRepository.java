package com.yukam.mypam.repository;

import com.yukam.mypam.domain.PackageType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the BenefitLevel entity.
 */
public interface PackageTypeRepository extends JpaRepository<PackageType, Long> {

}
