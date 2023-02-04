package com.entity.manytomany;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *  双向多对多关系实体类
 * @author Alex McAvoy
 * @date 2022/3/11 18:24
 * @version 1.0
 **/
@Table(name = "categories")
@Entity
public class Category {
    @GeneratedValue
    @Id
    private Integer id;
    @Column(name = "category_name")
    private String categoryName;
    @ManyToMany(mappedBy = "categories")
    private Set<Item> items = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
