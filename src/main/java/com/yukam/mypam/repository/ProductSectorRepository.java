package com.yukam.mypam.repository;

import com.yukam.mypam.domain.ProductSector;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the ProductSector entity.
 */
public interface ProductSectorRepository extends JpaRepository<ProductSector, Long> {

}
