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
@Table(name = "CONTACT_DETAIL")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ContactDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "contact_detail_id")
    private long id;

    @Size(max = 100)
    @Column(name = "email")
    private String email;

    @Size(max = 20)
    @Column(name = "mobile")
    private String mobile;

    @Size(max = 20)
    @Column(name = "home_phone")
    private String homePhone;

    @Size(max = 20)
    @Column(name = "fax")
    private String fax;

    @Size(max = 20)
    @Column(name = "phone_other")
    private String phoneOther;

    @JoinColumn(name = "contact_person_id")
    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private ContactPerson contactPerson;

    @JoinColumn(name = "address_id")
    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Address address;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "contact", fetch = FetchType.EAGER)
//    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//    private Set<Address> addresses;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPhoneOther() {
        return phoneOther;
    }

    public void setPhoneOther(String phoneOther) {
        this.phoneOther = phoneOther;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContactDetail contact = (ContactDetail) o;

        if (id != contact.id) {
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
        return "Contact{" +
                "id=" + id +
                ", email=" + email + '\'' +
                ", mobile=" + mobile + '\'' +
                ", homePhone=" + homePhone + '\'' +
                ", fax=" + fax + '\'' +
                ", phoneOther=" + phoneOther + '\'' +
                ", address=" + (address!=null?address:0) +
                '}';
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
