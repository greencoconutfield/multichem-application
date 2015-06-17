package com.yukam.mypam.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A Quotation item detail.
 */
@Entity
@Table(name = "QUOTATION_ITEM_DETAIL")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QuotationItemDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "quotation_item_id")
    private long id;

    @JoinColumn(name = "product_id")
    @OneToOne
    private Product product;

//    @Size(min = 1, max = 20)
    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @JoinColumn(name = "pricing_type_id")
    @OneToOne
    private PricingType pricingType;

    //    @Size(min = 1, max = 20)
    @Column(name = "package_quantity")
    private BigDecimal quantity;

    //    @Size(min = 1, max = 20)
    @Column(name = "lead_time")
    private BigDecimal leadTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }


    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(BigDecimal leadTime) {
        this.leadTime = leadTime;
    }


    public PricingType getPricingType() {
        return pricingType;
    }

    public void setPricingType(PricingType pricingType) {
        this.pricingType = pricingType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuotationItemDetail)) return false;

        QuotationItemDetail that = (QuotationItemDetail) o;

        if (id != that.id) return false;
        if (pricingType != null ? !pricingType.equals(that.pricingType) : that.pricingType != null) return false;
        if (product != null ? !product.equals(that.product) : that.product != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (leadTime != null ? !leadTime.equals(that.leadTime) : that.leadTime != null) return false;
        if (unitPrice != null ? !unitPrice.equals(that.unitPrice) : that.unitPrice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (unitPrice != null ? unitPrice.hashCode() : 0);
        result = 31 * result + (pricingType != null ? pricingType.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (leadTime != null ? leadTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "QuotationItemDetail{" +
                "id=" + id +
                ", product=" + product +
                ", unitPrice=" + unitPrice +
                ", pricingType=" + pricingType +
                ", quantity=" + quantity +
                '}';
    }
}
