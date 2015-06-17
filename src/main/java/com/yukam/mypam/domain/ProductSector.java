package com.yukam.mypam.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A ProductSector.
 */
@Entity
@Table(name = "PRODUCT_SECTOR")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProductSector implements Serializable {

    public ProductSector() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "product_sector_id")
    private long id;

    @JoinColumn(name = "sub_industry_sector_id")
    @OneToOne
    private SubIndustrySector subIndustrySector;

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

    public SubIndustrySector getSubIndustrySector() {
        return subIndustrySector;
    }

    public void setSubIndustrySector(SubIndustrySector subIndustrySector) {
        this.subIndustrySector = subIndustrySector;
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
        if (this == o) return true;
        if (!(o instanceof ProductSector)) return false;

        ProductSector that = (ProductSector) o;

        if (id != that.id) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (subIndustrySector != null ? !subIndustrySector.equals(that.subIndustrySector) : that.subIndustrySector != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (subIndustrySector != null ? subIndustrySector.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductSector{" +
                "id=" + id +
                ", subIndustrySector=" + subIndustrySector +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
