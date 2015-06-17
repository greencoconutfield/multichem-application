package com.yukam.mypam.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A IndustrySector.
 */
@Entity
@Table(name = "SUB_INDUSTRY_SECTOR")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SubIndustrySector implements Serializable {

    public SubIndustrySector() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "sub_industry_sector_id")
    private long id;

    @JoinColumn(name = "industry_sector_id")
    @OneToOne
    private IndustrySector parent;

    @Size(min = 1, max = 50)
    private String name;

    @Size(max = 500)
    private String description;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        SubIndustrySector subIndustrysector = (SubIndustrySector) o;

        if (id != subIndustrysector.id) {
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
        return "IndustrySector{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }

    public IndustrySector getParent() {
        return parent;
    }

    public void setParent(IndustrySector parent) {
        this.parent = parent;
    }
}
