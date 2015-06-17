package com.yukam.mypam.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * A Quotation.
 */
@Entity
@Table(name = "QUOTATION")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Quotation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "quotation_id")
    private long id;

    @Size(min = 1, max = 50)
    @Column(name = "quotation_name")
    private String quotationName;

    @Size(min = 1, max = 50)
    @Column(name = "company")
    private String company;

    @Size(min = 1, max = 50)
    @Column(name = "quotation_type")
    private String quotationType;

    @Size(min = 1, max = 500)
    @Column(name = "description")
    private String description;

    @JoinColumn(name = "customer_id")
    @OneToOne
    private Customer customer;
//    @Size(min = 1, max = 20)
    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "unit")
    private Integer unit;

    @Size(min = 1, max = 20)
    @Column(name = "unit_type")
    private String unitType;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinTable(
            name = "QUOTATION_ITEM",
            joinColumns = {@JoinColumn(name = "quotation_id", referencedColumnName = "quotation_id")},
            inverseJoinColumns = {@JoinColumn(name = "quotation_item_id", referencedColumnName = "quotation_item_id")})
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QuotationItemDetail> quotationItemDetails;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuotationName() {
        return quotationName;
    }

    public void setQuotationName(String quotationName) {
        this.quotationName = quotationName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getQuotationType() {
        return quotationType;
    }

    public void setQuotationType(String quotationType) {
        this.quotationType = quotationType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public Set<QuotationItemDetail> getQuotationItemDetails() {
        return quotationItemDetails;
    }

    public void setQuotationItemDetails(Set<QuotationItemDetail> quotationItemDetails) {
        this.quotationItemDetails = quotationItemDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quotation)) return false;

        Quotation quotation = (Quotation) o;

        if (id != quotation.id) return false;
        if (company != null ? !company.equals(quotation.company) : quotation.company != null) return false;
        if (customer != null ? !customer.equals(quotation.customer) : quotation.customer != null) return false;
        if (description != null ? !description.equals(quotation.description) : quotation.description != null)
            return false;
        if (quotationItemDetails != null ? !quotationItemDetails.equals(quotation.quotationItemDetails) : quotation.quotationItemDetails != null)
            return false;
        if (quotationName != null ? !quotationName.equals(quotation.quotationName) : quotation.quotationName != null)
            return false;
        if (quotationType != null ? !quotationType.equals(quotation.quotationType) : quotation.quotationType != null)
            return false;
        if (unit != null ? !unit.equals(quotation.unit) : quotation.unit != null) return false;
        if (unitPrice != null ? !unitPrice.equals(quotation.unitPrice) : quotation.unitPrice != null) return false;
        if (unitType != null ? !unitType.equals(quotation.unitType) : quotation.unitType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (quotationName != null ? quotationName.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (quotationType != null ? quotationType.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (unitPrice != null ? unitPrice.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (unitType != null ? unitType.hashCode() : 0);
        result = 31 * result + (quotationItemDetails != null ? quotationItemDetails.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Quotation{" +
                "id=" + id +
                ", quotationName='" + quotationName + '\'' +
                ", company='" + company + '\'' +
                ", quotationType=" + quotationType + '\'' +
                ", description=" + description + '\'' +
                ", unitPrice=" + unitPrice + '\'' +
                ", unit=" + unit + '\'' +
                ", unitType=" + unitType +  '\'' +
                ", industrySector=" +  +
                '}';
    }
}
