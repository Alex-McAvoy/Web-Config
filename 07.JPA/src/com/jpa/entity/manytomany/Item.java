package com.jpa.entity.manytomany;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 双向多对多关系实体类
 * @Author: Alex McAvoy
 * @Date: 2022/3/11 18:25
 * @Version: 1.0
 **/
@Table(name = "items")
@Entity
public class Item {
    @GeneratedValue
    @Id
    private Integer id;
    @Column(name = "item_name")
    private String itemName;
    /**
     *   @ManyToMany 映射多对多关系
     *   @JoinTable 来映射中间表
     *    - name 为中间表名
     *    - joinColumns 映射当前类所在的表在中间表中的外键
     *      - name 指定外键列的列名
     *      - referencedColumnName 指定外键列关联当前表的哪一列
     *    - inverseJoinColumns 映射关联的类所在表的外键
     *      - name 指定外键列的列名
     *      - referencedColumnName 指定外键列关联当前表的哪一列
     * **/
    @JoinTable(name = "item_category",
            joinColumns = {@JoinColumn(name = "item_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id",referencedColumnName = "id")})
    @ManyToMany
    private Set<Category> categories = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
