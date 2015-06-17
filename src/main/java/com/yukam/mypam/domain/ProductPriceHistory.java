package com.yukam.mypam.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;
import com.yukam.mypam.domain.util.CustomLocalDateSerializer;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A Product.
 */
@Entity
@Table(name = "PRODUCT_PRICE_HISTORY")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProductPriceHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "product_price_history_id")
    private long id;

    @Column(name = "product_id")
    private long product_id;

    @Column(name = "customer_id")
    private long customer_id;

    @JoinColumn(name = "pricing_type_id")
    @OneToOne
    private PricingType pricingType;

    @JoinColumn(name = "currency_id")
    @OneToOne
    private Currency currency;


    @Column(name = "value")
    private BigDecimal value;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @Column(name = "expired_date")
    private LocalDate  expiredDate;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @Column(name = "last_update_date")
    private LocalDate lastUpdate;

    @Column(name = "is_current_used")
    private boolean isCurrentUsed;

//    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "PRODUCT_BUYING_PRICE",
//            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "product_id")},
//            inverseJoinColumns = {@JoinColumn(name = "supplier_buying_price_id", referencedColumnName = "supplier_buying_price_id")})
//    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//    private Set<SupplierBuyingPriceDetail> supplierBuyingPriceDetails;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductPriceHistory product = (ProductPriceHistory) o;

        if (id != product.id) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" +  + '\'' +
                ", productType=" +  + '\'' +
                ", description=" +  + '\'' +
                ", unitPrice=" +  + '\'' +
                ", unit="  + '\'' +
                ", industrySector="  +
                '}';
    }



    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public boolean isCurrentUsed() {
        return isCurrentUsed;
    }

    public void setCurrentUsed(boolean isCurrentUsed) {
        this.isCurrentUsed = isCurrentUsed;
    }

    public PricingType getPricingType() {
        return pricingType;
    }

    public void setPricingType(PricingType pricingType) {
        this.pricingType = pricingType;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }
}
