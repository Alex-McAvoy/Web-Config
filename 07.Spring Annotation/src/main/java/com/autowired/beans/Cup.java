package com.autowired.beans;

import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/6/28 0:00
 * @Version: 1.0
 **/
@Component
public class Cup {
    private String name;
    private Integer id;

    public Cup() {
    }

    public Cup(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cup{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
