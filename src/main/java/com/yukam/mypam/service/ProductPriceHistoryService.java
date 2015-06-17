package com.yukam.mypam.service;

import com.yukam.mypam.domain.ProductPriceHistory;
import com.yukam.mypam.repository.ProductPriceHistoryRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service for sending e-mails.
 * <p/>
 * <p>
 * We use the @Async annotation to send e-mails asynchronously.
 * </p>
 */
@Service
public class ProductPriceHistoryService {

    @Inject
    private ProductPriceHistoryRepository productPriceHistoryRepository;

    public List<ProductPriceHistory> getProductPriceHistory(Long productId){
       return productPriceHistoryRepository.getProductPriceHistory(productId);
    }

    public void addProductPriceHistory(ProductPriceHistory productPriceHistory){
        productPriceHistoryRepository.save(productPriceHistory);
    }
}
