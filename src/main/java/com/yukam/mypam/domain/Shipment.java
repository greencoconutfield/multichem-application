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
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * A Shipment.
 */
@Entity
@Table(name = "SHIPMENT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Shipment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "shipment_id")
    private long id;

    @Size(min = 0, max = 50)
    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "SHIPMENT_CUSTOMER",
            joinColumns = {@JoinColumn(name = "shipment_id", referencedColumnName = "shipment_id")},
            inverseJoinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")})
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Customer> customers;

    @JoinColumn(name = "supplier_id")
    @OneToOne(fetch = FetchType.EAGER)
    private Supplier supplier;

    @JoinColumn(name = "created_by_user")
    @OneToOne
    private User createdByUser;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @Column(name = "dispatched_date")
    private LocalDate dispatchedDate;

    @Size(min = 0, max = 50)
    @Column(name = "dispatched_from")
    private String dispatchedFrom;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @Size(min = 0, max = 100)
    @Column(name = "arrival_location")
    private String arrivalLocation;

    @Size(min = 0, max = 50)
    @Column(name = "status")
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    public LocalDate getDispatchedDate() {
        return dispatchedDate;
    }

    public void setDispatchedDate(LocalDate dispatchedDate) {
        this.dispatchedDate = dispatchedDate;
    }

    public String getDispatchedFrom() {
        return dispatchedFrom;
    }

    public void setDispatchedFrom(String dispatchedFrom) {
        this.dispatchedFrom = dispatchedFrom;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Shipment shipment = (Shipment) o;

        if (id != shipment.id) {
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
        return "Shipment{" +
                "id=" + id + '\'' +
                ", description='" + description + '\'' +
                ", customers='" + (customers!=null?customers.size():0) +
                ", supplier='" + (supplier!=null?supplier.getId():null) +  '\'' +
                ", createdByUser='" + (createdByUser!=null?createdByUser.getLogin():null) + '\'' +
                ", dispatchedDate='" + dispatchedDate + '\'' +
                ", dispatchedFrom='" + dispatchedFrom + '\'' +
                ", arrivalDate='" + arrivalDate + '\'' +
                ", arrivalLocation='" + arrivalLocation + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
