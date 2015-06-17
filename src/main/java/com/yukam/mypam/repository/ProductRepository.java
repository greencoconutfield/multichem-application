package com.yukam.mypam.repository;

import com.yukam.mypam.domain.Product;
        import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Product entity.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}
