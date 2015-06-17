package com.yukam.mypam.repository;

import com.yukam.mypam.domain.Product;
import com.yukam.mypam.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Spring Data JPA repository for the Supplier entity.
 */
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query("select p from Product p where p.supplier.id = ?1")
    List<Product> getAssociatedProducts(Long supplierId);

}
