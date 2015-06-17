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
@Table(name = "SUPPLIER")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Supplier implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Supplier supplier = (Supplier) o;

        if (id != supplier.id) {
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
        return "Supplier{" +
                "id=" + id +
                ", supplierName='" + supplierName + '\'' +
                ", contact='" +
                '}';
    }

    public ContactDetail getContactDetail() {
        return contactDetail;
    }

    public void setContactDetail(ContactDetail contactDetail) {
        this.contactDetail = contactDetail;
    }
}
