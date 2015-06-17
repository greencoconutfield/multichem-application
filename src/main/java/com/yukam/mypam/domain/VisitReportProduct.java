package com.yukam.mypam.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A Product.
 */
@Entity
@Table(name = "VISIT_REPORT_PRODUCT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class VisitReportProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "visit_report_product_id")
    private long id;

    @Size(min = 1, max = 50)
    @Column(name = "product_name")
    private String productName;

    @Size(max = 100)
    @Column(name = "type")
    private String type;

    @Size(max = 1000)
    @Column(name = "description")
    private String description;

    @JoinColumn(name = "sub_industry_sector_id")
    @OneToOne
    private SubIndustrySector subIndustrySector;

    @JoinColumn(name = "product_sector_id")
    @OneToOne
    private ProductSector productSector;

    @JoinColumn(name = "supplier_id")
    @OneToOne
    private VisitReportSupplier supplier;

    @JoinColumn(name = "product_id")
    @OneToOne
    private Product product;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SubIndustrySector getSubIndustrySector() {
        return subIndustrySector;
    }

    public void setSubIndustrySector(SubIndustrySector subIndustrySector) {
        this.subIndustrySector = subIndustrySector;
    }

    public ProductSector getProductSector() {
        return productSector;
    }

    public void setProductSector(ProductSector productSector) {
        this.productSector = productSector;
    }

    public VisitReportSupplier getSupplier() {
        return supplier;
    }

    public void setSupplier(VisitReportSupplier supplier) {
        this.supplier = supplier;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitReportProduct)) return false;

        VisitReportProduct that = (VisitReportProduct) o;

        if (id != that.id) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (product != null ? !product.equals(that.product) : that.product != null) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (productSector != null ? !productSector.equals(that.productSector) : that.productSector != null)
            return false;
        if (subIndustrySector != null ? !subIndustrySector.equals(that.subIndustrySector) : that.subIndustrySector != null)
            return false;
        if (supplier != null ? !supplier.equals(that.supplier) : that.supplier != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (subIndustrySector != null ? subIndustrySector.hashCode() : 0);
        result = 31 * result + (productSector != null ? productSector.hashCode() : 0);
        result = 31 * result + (supplier != null ? supplier.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VisitReportProduct{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", subIndustrySector=" + subIndustrySector +
                ", productSector=" + productSector +
                ", supplier=" + supplier +
                ", product=" + product +
                '}';
    }
}
