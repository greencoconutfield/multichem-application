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

/**
 * A Product.
 */
@Entity
@Table(name = "VISIT_REPORT_ACTION_ITEM_DETAIL")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ActionDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "visit_report_action_item_id")
    private long id;

    @JoinColumn(name = "employee_id")
    @OneToOne
    private Employee employee;

    @Column(name = "status")
    private String status;


    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @Column(name = "dead_line")
    private LocalDate deadLine;

    @Column(name = "detail")
    private String detail;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActionDetail that = (ActionDetail) o;

        if (id != that.id) return false;
        if (!deadLine.equals(that.deadLine)) return false;
        if (!detail.equals(that.detail)) return false;
        if (!employee.equals(that.employee)) return false;
        if (!status.equals(that.status)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + employee.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + deadLine.hashCode();
        result = 31 * result + detail.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ActionDetail{" +
                "id=" + id +
                ", employee=" + employee +
                ", status='" + status + '\'' +
                ", deadLine=" + deadLine +
                ", detail='" + detail + '\'' +
                '}';
    }
}
