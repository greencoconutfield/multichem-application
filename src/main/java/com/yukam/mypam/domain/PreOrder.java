package com.yukam.mypam.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;
import com.yukam.mypam.domain.util.CustomLocalDateSerializer;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A PreOrder.
 */
@Entity
@Table(name = "PRE_ORDER")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PreOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "pre_order_id")
    private long id;

    @JoinColumn(name = "customer_id")
    @OneToOne(fetch = FetchType.EAGER)
    private Customer customer;

    @Size(min = 1, max = 50)
    @Column(name = "created_by_user")
    private String createdByUser;

    @JoinColumn(name = "sales_person")
    @OneToOne(fetch = FetchType.EAGER)
    private Employee salesPerson;

    @NotNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "entry_location")
    private String entryLocation;

    @Column(name = "description")
    private String description;

    @Size(min = 0, max = 20)
    @Column(name = "status")
    private String status;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public Employee getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(Employee salesPerson) {
        this.salesPerson = salesPerson;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getEntryLocation() {
        return entryLocation;
    }

    public void setEntryLocation(String entryLocation) {
        this.entryLocation = entryLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PreOrder preorder = (PreOrder) o;

        if (id != preorder.id) {
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
        return "PreOrder{" +
                "id=" + id +
                ", customer='" + ((customer != null)?customer.getCustomerName():null) + '\'' +
                ", status=" + status +
                '}';
    }
}
