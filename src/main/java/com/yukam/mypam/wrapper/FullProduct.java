package com.yukam.mypam.wrapper;

import com.yukam.mypam.domain.Product;
import com.yukam.mypam.domain.ProductPriceHistory;

import java.util.List;

/**
 * Created by Wish on 11/16/2014.
 */
public class FullProduct {

    private Product product;

    private List<ProductPriceHistory> productPriceHistoryList;

    public List<ProductPriceHistory> getProductPriceHistoryList() {
        return productPriceHistoryList;
    }

    public void setProductPriceHistoryList(List<ProductPriceHistory> productPriceHistoryList) {
        this.productPriceHistoryList = productPriceHistoryList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
