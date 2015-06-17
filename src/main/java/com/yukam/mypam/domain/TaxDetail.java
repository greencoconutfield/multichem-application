package com.yukam.mypam.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Tax detail.
 */
@Entity
@Table(name = "TAX_DETAIL")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TaxDetail implements Serializable {

    public TaxDetail() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "tax_detail_id")
    private long id;

    @JoinColumn(name = "default_tax_detail_id")
    @OneToOne
    private DefaultTaxDetail defaultTaxDetail;

    @Column(name = "insurance")
    private BigDecimal insurance;

    @Column(name = "supplier_inland_cost")
    private BigDecimal supplierInlandCost;

    @Column(name = "freight_cost")
    private BigDecimal freightCost;

    @Column(name = "local_clearance")
    private BigDecimal localClearance;

    @Column(name = "import_tax")
    private BigDecimal importTax;

    @Column(name = "admin_charge")
    private BigDecimal adminCharge;


    @Column(name = "tt_fee")
    private BigDecimal ttFee;

    @Column(name = "extra_fee_1")
    private BigDecimal extraFee1;

    @Column(name = "extra_fee_2")
    private BigDecimal extraFee2;

    @Column(name = "extra_fee_3")
    private BigDecimal extraFee3;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DefaultTaxDetail getDefaultTaxDetail() {
        return defaultTaxDetail;
    }

    public void setDefaultTaxDetail(DefaultTaxDetail defaultTaxDetail) {
        this.defaultTaxDetail = defaultTaxDetail;
    }
    public BigDecimal getTtFee() {
        return ttFee;
    }

    public void setTtFee(BigDecimal ttFee) {
        this.ttFee = ttFee;
    }

    public BigDecimal getAdminCharge() {
        return adminCharge;
    }

    public void setAdminCharge(BigDecimal adminCharge) {
        this.adminCharge = adminCharge;
    }

    public BigDecimal getImportTax() {
        return importTax;
    }

    public void setImportTax(BigDecimal importTax) {
        this.importTax = importTax;
    }

    public BigDecimal getLocalClearance() {
        return localClearance;
    }

    public void setLocalClearance(BigDecimal localClearance) {
        this.localClearance = localClearance;
    }

    public BigDecimal getFreightCost() {
        return freightCost;
    }

    public void setFreightCost(BigDecimal freightCost) {
        this.freightCost = freightCost;
    }

    public BigDecimal getSupplierInlandCost() {
        return supplierInlandCost;
    }

    public void setSupplierInlandCost(BigDecimal supplierInlandCost) {
        this.supplierInlandCost = supplierInlandCost;
    }

    public void setInsurance(BigDecimal insurance) {
        this.insurance = insurance;
    }

    public BigDecimal getInsurance() {
        return insurance;
    }

    public BigDecimal getExtraFee3() {
        return extraFee3;
    }

    public void setExtraFee3(BigDecimal extraFee3) {
        this.extraFee3 = extraFee3;
    }

    public BigDecimal getExtraFee2() {
        return extraFee2;
    }

    public void setExtraFee2(BigDecimal extraFee2) {
        this.extraFee2 = extraFee2;
    }

    public BigDecimal getExtraFee1() {
        return extraFee1;
    }

    public void setExtraFee1(BigDecimal extraFee1) {
        this.extraFee1 = extraFee1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaxDetail)) return false;

        TaxDetail taxDetail = (TaxDetail) o;

        if (id != taxDetail.id) return false;
        if (!adminCharge.equals(taxDetail.adminCharge)) return false;
        if (!insurance.equals(taxDetail.insurance)) return false;
        if (!extraFee1.equals(taxDetail.extraFee1)) return false;
        if (!extraFee2.equals(taxDetail.extraFee2)) return false;
        if (!extraFee3.equals(taxDetail.extraFee3)) return false;
        if (!freightCost.equals(taxDetail.freightCost)) return false;
        if (!importTax.equals(taxDetail.importTax)) return false;
        if (!localClearance.equals(taxDetail.localClearance)) return false;
        if (!supplierInlandCost.equals(taxDetail.supplierInlandCost)) return false;
        if (!ttFee.equals(taxDetail.ttFee)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + defaultTaxDetail.hashCode();
        result = 31 * result + insurance.hashCode();
        result = 31 * result + supplierInlandCost.hashCode();
        result = 31 * result + freightCost.hashCode();
        result = 31 * result + localClearance.hashCode();
        result = 31 * result + importTax.hashCode();
        result = 31 * result + adminCharge.hashCode();
        result = 31 * result + ttFee.hashCode();
        result = 31 * result + extraFee1.hashCode();
        result = 31 * result + extraFee2.hashCode();
        result = 31 * result + extraFee3.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TaxDetail{" +
                "id=" + id +
                ", defaultTaxDetail=" + defaultTaxDetail +
                ", insurance=" + insurance +
                ", supplierInlandCost=" + supplierInlandCost +
                ", freightCost=" + freightCost +
                ", localClearance=" + localClearance +
                ", importTax=" + importTax +
                ", adminCharge=" + adminCharge +
                ", ttFee=" + ttFee +
                ", extraFee1=" + extraFee1 +
                ", extraFee2=" + extraFee2 +
                ", extraFee3=" + extraFee3 +
                '}';
    }


}
