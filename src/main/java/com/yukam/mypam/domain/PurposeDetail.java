package com.yukam.mypam.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A Product.
 */
@Entity
@Table(name = "VISIT_REPORT_PURPOSE_ITEM_DETAIL")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PurposeDetail implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "visit_report_purpose_item_id")

    private long id;

    @Override
    public String toString() {
        return "PurposeDetail{" +
                "id=" + id +
                ", detail='" + detail + '\'' +
                '}';
    }

    @Column(name = "detail")
    private String detail;


    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PurposeDetail that = (PurposeDetail) o;

        if (id != that.id) return false;
        if (!detail.equals(that.detail)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + detail.hashCode();
        return result;
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


   }
