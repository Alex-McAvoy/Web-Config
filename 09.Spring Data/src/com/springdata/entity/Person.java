package com.springdata.entity;


import javax.persistence.*;
import java.util.Date;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/3/12 10:04
 * @Version: 1.0
 **/
@Table(name = "persons")
@Entity
public class Person {
    @GeneratedValue
    @Id
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "birth")
    @Temporal(TemporalType.DATE)
    private Date birth;
    @JoinColumn(name = "address_id")
    @ManyToOne
    private Address address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", address=" + address +
                '}';
    }
}
