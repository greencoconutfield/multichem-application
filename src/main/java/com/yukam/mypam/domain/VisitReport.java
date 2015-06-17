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
import java.io.Serializable;
import java.util.Set;

/**
 * A Visit report.
 */
@Entity
@Table(name = "VISIT_REPORT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class VisitReport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "visit_report_id")
    private long id;

    @JoinColumn(name = "customer_id")
    @OneToOne
    private VisitReportCustomer customer;

    @JoinColumn(name = "employee_id")
    @OneToOne
    private Employee employee;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "customer_overview")
    private String customerOverview;

    @Column(name = "facing_problem")
    private String facingProblem;

    @Column(name = "status")
    private String status;


    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @Column(name = "date")

    private LocalDate date;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinTable(
            name = "VISIT_REPORT_PRODUCT_ITEM",
            joinColumns = {@JoinColumn(name = "visit_report_id", referencedColumnName = "visit_report_id")},
            inverseJoinColumns = {@JoinColumn(name = "visit_report_product_item_id", referencedColumnName = "visit_report_product_item_id")})
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<VisitReportProductItem> visitReportProductItems;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinTable(
            name = "VISIT_REPORT_PURPOSE_ITEM",
            joinColumns = {@JoinColumn(name = "visit_report_id", referencedColumnName = "visit_report_id")},
            inverseJoinColumns = {@JoinColumn(name = "visit_report_purpose_item_id", referencedColumnName = "visit_report_purpose_item_id")})
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PurposeDetail> purposes;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinTable(
            name = "VISIT_REPORT_ACTION_ITEM",
            joinColumns = {@JoinColumn(name = "visit_report_id", referencedColumnName = "visit_report_id")},
            inverseJoinColumns = {@JoinColumn(name = "visit_report_action_item_id", referencedColumnName = "visit_report_action_item_id")})
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ActionDetail> actions;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public VisitReportCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(VisitReportCustomer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getCustomerOverview() {
        return customerOverview;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCustomerOverview(String customerOverview) {
        this.customerOverview = customerOverview;
    }

    public String getFacingProblem() {
        return facingProblem;
    }

    public void setFacingProblem(String facingProblem) {
        this.facingProblem = facingProblem;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<VisitReportProductItem> getVisitReportProductItems() {
        return visitReportProductItems;
    }

    public void setVisitReportProductItems(Set<VisitReportProductItem> visitReportProductItems) {
        this.visitReportProductItems = visitReportProductItems;
    }

    public Set<PurposeDetail> getPurposes() {
        return purposes;
    }

    public void setPurposes(Set<PurposeDetail> purposes) {
        this.purposes = purposes;
    }

    public Set<ActionDetail> getActions() {
        return actions;
    }

    public void setActions(Set<ActionDetail> actions) {
        this.actions = actions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitReport)) return false;

        VisitReport that = (VisitReport) o;

        if (id != that.id) return false;
        if (actions != null ? !actions.equals(that.actions) : that.actions != null) return false;
        if (contactPerson != null ? !contactPerson.equals(that.contactPerson) : that.contactPerson != null)
            return false;
        if (customer != null ? !customer.equals(that.customer) : that.customer != null) return false;
        if (customerOverview != null ? !customerOverview.equals(that.customerOverview) : that.customerOverview != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null)
            return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (employee != null ? !employee.equals(that.employee) : that.employee != null) return false;
        if (facingProblem != null ? !facingProblem.equals(that.facingProblem) : that.facingProblem != null)
            return false;
        if (purposes != null ? !purposes.equals(that.purposes) : that.purposes != null) return false;
        if (visitReportProductItems != null ? !visitReportProductItems.equals(that.visitReportProductItems) : that.visitReportProductItems != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (employee != null ? employee.hashCode() : 0);
        result = 31 * result + (contactPerson != null ? contactPerson.hashCode() : 0);
        result = 31 * result + (customerOverview != null ? customerOverview.hashCode() : 0);
        result = 31 * result + (facingProblem != null ? facingProblem.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (visitReportProductItems != null ? visitReportProductItems.hashCode() : 0);
        result = 31 * result + (purposes != null ? purposes.hashCode() : 0);
        result = 31 * result + (actions != null ? actions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VisitReport{" +
                "id=" + id +
                ", customer=" + customer +
                ", employee=" + employee +
                ", contactPerson='" + contactPerson + '\'' +
                ", customerOverview='" + customerOverview + '\'' +
                ", facingProblem='" + facingProblem + '\'' +
                ", date=" + date +
                ", visitReportProductItems=" + visitReportProductItems +
                ", purposes=" + purposes +
                ", actions=" + actions +
                '}';
    }
}
