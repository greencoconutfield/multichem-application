package com.yukam.mypam.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A Customer.
 */
@Entity
@Table(name = "CUSTOMER")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "customer_id")
    private long id;

    @Size(min = 1, max = 100)
    @Column(name = "customer_name")
    private String customerName;

    @Size(max = 500)
    @Column(name = "description")
    private String description;

    @JoinColumn(name = "contact_detail_id")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ContactDetail contactDetail;

    @JoinColumn(name = "industry_sector_id")
    @OneToOne(fetch = FetchType.EAGER)
    private IndustrySector industrySector;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ContactDetail getContactDetail() {
        return contactDetail;
    }

    public void setContact(ContactDetail contactDetail) {
        this.contactDetail = contactDetail;
    }

    public IndustrySector getIndustrySector() {
        return industrySector;
    }

    public void setIndustrySector(IndustrySector industrySector) {
        this.industrySector = industrySector;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Customer customer = (Customer) o;

        if (id != customer.id) {
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
        return "Customer{" +
                "id=" + id + '\'' +
                ", customerName=" + customerName +
                ", contactDetail='" + (contactDetail!=null?contactDetail.getId():null) + '\'' +
                ", industrySector=" + (industrySector!=null?industrySector.getId():null) +
                '}';
    }
}
