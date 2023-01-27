package com.entity;

import javax.persistence.*;

/**
 * address 实体类
 * @author Alex McAvoy
 * @date 2022/3/13 13:40
 * @version 1.0
 **/
@Table(name = "address")
@Entity
public class Address {
    @GeneratedValue
    @Id
    private Integer id;
    @Column(name = "province")
    private String province;
    @Column(name = "city")
    private String city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
