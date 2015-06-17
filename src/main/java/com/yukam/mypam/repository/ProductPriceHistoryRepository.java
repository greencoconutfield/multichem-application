package com.yukam.mypam.repository;

import com.yukam.mypam.domain.ProductPriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Spring Data JPA repository for the PricingType entity.
 */
public interface ProductPriceHistoryRepository extends JpaRepository<ProductPriceHistory, Long> {

    @Query("select pPriceHistory from ProductPriceHistory pPriceHistory where pPriceHistory.id in (select max(p.id) from    ProductPriceHistory  p  where p.product_id = ?1   group by p.value)")
    List<ProductPriceHistory> getProductPriceHistory(Long productId);


    @Query("select pPriceHistory from ProductPriceHistory pPriceHistory where pPriceHistory.id in (select max(p.id) from    ProductPriceHistory  p  where p.customer_id = ?1 AND p.product_id = ?2   group by p.value)")
    List<ProductPriceHistory> getCustomerProductPriceHistory(Long customerId, Long productId);

}
