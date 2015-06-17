package com.yukam.mypam.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A Visit product report item detail.
 */
@Entity
@Table(name = "VISIT_REPORT_PRODUCT_ITEM_DETAIL")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class VisitReportProductItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "visit_report_product_item_id")
    private long id;

    @JoinColumn(name = "visit_report_product_id")
    @OneToOne
    private VisitReportProduct visitReportProduct;

    //    @Size(min = 1, max = 20)
    @Column(name = "quantity")
    private BigDecimal quantity;

    @JoinColumn(name = "unit_id")
    @OneToOne
    private Unit unit;

    //    @Size(min = 1, max = 20)
    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "notes")
    private String notes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public VisitReportProduct getVisitReportProduct() {
        return visitReportProduct;
    }

    public void setVisitReportProduct(VisitReportProduct product) {
        this.visitReportProduct = product;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitReportProductItem)) return false;

        VisitReportProductItem that = (VisitReportProductItem) o;

        if (id != that.id) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (unit != null ? !unit.equals(that.unit) : that.unit != null) return false;
        if (unitPrice != null ? !unitPrice.equals(that.unitPrice) : that.unitPrice != null) return false;
        if (visitReportProduct != null ? !visitReportProduct.equals(that.visitReportProduct) : that.visitReportProduct != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (visitReportProduct != null ? visitReportProduct.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (unitPrice != null ? unitPrice.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VisitReportProductItem{" +
                "id=" + id +
                ", visitReportProduct=" + visitReportProduct +
                ", quantity=" + quantity +
                ", unit=" + unit +
                ", unitPrice=" + unitPrice +
                ", notes='" + notes + '\'' +
                '}';
    }
}