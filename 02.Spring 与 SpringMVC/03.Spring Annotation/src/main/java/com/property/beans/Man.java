package com.property.beans;

import org.springframework.beans.factory.annotation.Value;

/**
 * TODO
 * @author Alex McAvoy
 * @date 2022/6/27 22:37
 * @version 1.0
 **/
public class Man {
    @Value("XX")
    private String name;
    @Value("#{20-2}")
    private Integer age;

    public Man() {
    }

    public Man(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Man{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
