package com.entity.onetomany;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *  一对多关系的实体类
 * @author Alex McAvoy
 * @date 2022/3/11 10:55
 * @version 1.0
 **/
@Table(name = "persons")
@Entity
public class Person {
    @GeneratedValue
    @Id
    private Integer id;
    @Column(name = "name")
    private String name;
    //使用 @OneToMany 映射一对多关系
    //使用 @JoinColumn 映射外键列的名称
    @JoinColumn(name = "person_id")
    @OneToMany
    private Set<Car> cars = new HashSet<>();

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

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}
