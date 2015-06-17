package com.yukam.mypam.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A Supplier.
 */
@Entity
@Table(name = "VISIT_REPORT_SUPPLIER")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class VisitReportSupplier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "supplier_id")
    private long id;

    @Size(min = 1, max = 100)
    @Column(name = "supplier_name")
    private String supplierName;

    @JoinColumn(name = "contact_detail_id")
    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private ContactDetail contactDetail;

//    @JoinColumn(name = "contact_id")
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Contact contact;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }


    public ContactDetail getContactDetail() {
        return contactDetail;
    }

    public void setContactDetail(ContactDetail contactDetail) {
        this.contactDetail = contactDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitReportSupplier)) return false;

        VisitReportSupplier that = (VisitReportSupplier) o;

        if (id != that.id) return false;
        if (contactDetail != null ? !contactDetail.equals(that.contactDetail) : that.contactDetail != null)
            return false;
        if (supplierName != null ? !supplierName.equals(that.supplierName) : that.supplierName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (supplierName != null ? supplierName.hashCode() : 0);
        result = 31 * result + (contactDetail != null ? contactDetail.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VisitReportSupplier{" +
                "id=" + id +
                ", supplierName='" + supplierName + '\'' +
                ", contactDetail=" + contactDetail +
                '}';
    }
}
