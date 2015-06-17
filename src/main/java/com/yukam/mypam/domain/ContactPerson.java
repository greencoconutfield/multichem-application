package com.yukam.mypam.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A Contact.
 */
@Entity
@Table(name = "CONTACT_PERSON")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ContactPerson implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "contact_person_id")
    private long id;

    @Size(max = 50)
    @Column(name = "title")
    private String title;

    @Size(max = 50)
    @Column(name = "full_name")
    private String fullName;

    @Size(max = 100)
    @Column(name = "email")
    private String email;

    @Size(max = 20)
    @Column(name = "mobile")
    private String mobile;

    @Size(max = 20)
    @Column(name = "home_phone")
    private String homePhone;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "contact", fetch = FetchType.EAGER)
//    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//    private Set<Address> addresses;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName(){
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContactPerson contactPerson = (ContactPerson) o;

        if (id != contactPerson.id) {
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
        return "ContactPerson{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email=" + email + '\'' +
                ", mobile=" + mobile + '\'' +
                ", homePhone=" + homePhone + '\'' +
                '}';
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
