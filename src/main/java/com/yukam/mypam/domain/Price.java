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
@Table(name = "PRICE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Price implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "price_id")
    private long id;

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
    private LocalDate expiredDate;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Price)) return false;

        Price price = (Price) o;

        if (id != price.id) return false;
        if (isCurrentUsed != price.isCurrentUsed) return false;
        if (currency != null ? !currency.equals(price.currency) : price.currency != null) return false;
        if (expiredDate != null ? !expiredDate.equals(price.expiredDate) : price.expiredDate != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(price.lastUpdate) : price.lastUpdate != null) return false;
        if (pricingType != null ? !pricingType.equals(price.pricingType) : price.pricingType != null) return false;
        if (value != null ? !value.equals(price.value) : price.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (pricingType != null ? pricingType.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (expiredDate != null ? expiredDate.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (isCurrentUsed ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", pricingType=" + pricingType +
                ", currency=" + currency +
                ", value=" + value +
                ", expiredDate=" + expiredDate +
                ", lastUpdate=" + lastUpdate +
                ", isCurrentUsed=" + isCurrentUsed +
                '}';
    }
}
