package com.autowired.dao;

import org.springframework.stereotype.Repository;

//自动注入时，名字默认为是类名首字母小写
@Repository
public class CupDao {
    private String label = "1";

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "CupDao{" +
                "label='" + label + '\'' +
                '}';
    }
}
