package com.yukam.mypam.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A Product.
 */
@Entity
@Table(name = "PRODUCT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "product_id")
    private long id;

    @Size(min = 1, max = 50)
    @Column(name = "product_name")
    private String productName;

    @Size(min = 1, max = 50)
    @Column(name = "product_type")
    private String productType;

    @Size(min = 1, max = 500)
    @Column(name = "description")
    private String description;

//    @Size(min = 1, max = 20)
    @JoinColumn(name = "price_id")
    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Price price;

    @JoinColumn(name = "sub_industry_sector_id")
    @OneToOne
    private SubIndustrySector subIndustrySector;

    @JoinColumn(name = "package_type_id")
    @OneToOne
    private PackageType packageType;

    @Column(name = "package_size")
    private BigDecimal packageSize;

    @Column(name = "minimum_order")
    private long minimumOrder;

    @JoinColumn(name = "unit_id")
    @OneToOne
    private Unit unit;

    @Column(name = "lead_time")
    private long leadTime;

    @JoinColumn(name = "supplier_id")
    @OneToOne
    private Supplier supplier;

    @JoinColumn(name = "tax_detail_id")
    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private TaxDetail taxDetail;


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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public BigDecimal getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(BigDecimal packageSize) {
        this.packageSize = packageSize;
    }

    public PackageType getPackageType() {
        return packageType;
    }

    public void setPackageType(PackageType packageType) {
        this.packageType = packageType;
    }

    public SubIndustrySector getSubIndustrySector() {
        return subIndustrySector;
    }

    public void setSubIndustrySector(SubIndustrySector subIndustrySector) {
        this.subIndustrySector = subIndustrySector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Product product = (Product) o;

        if (id != product.id) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public Supplier getSupplier() {
        return supplier;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", subIndustrySector=" + subIndustrySector +
                ", packageType=" + packageType +
                ", packageSize=" + packageSize +
                ", minimumOrder=" + minimumOrder +
                ", unit=" + unit +
                ", leadTime=" + leadTime +
                ", supplier=" + supplier +
                ", taxDetail=" + taxDetail +
                '}';
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public long getMinimumOrder() {
        return minimumOrder;
    }

    public void setMinimumOrder(long minimumOrder) {
        this.minimumOrder = minimumOrder;
    }

    public TaxDetail getTaxDetail() {
        return taxDetail;
    }

    public void setTaxDetail(TaxDetail taxDetail) {
        this.taxDetail = taxDetail;
    }

    public long getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(long leadTime) {
        this.leadTime = leadTime;
    }
}
