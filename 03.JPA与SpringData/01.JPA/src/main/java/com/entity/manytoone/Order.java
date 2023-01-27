package com.entity.manytoone;

import com.entity.basic.User;

import javax.persistence.*;

/**
 *  多对一关系的实体类
 * @author Alex McAvoy
 * @date 2022/3/11 10:28
 * @version 1.0
 **/
@Table(name = "orders")
@Entity
public class Order {
    @GeneratedValue
    @Id
    private Integer id;
    @Column(name = "order_name")
    private String orderName;
    //@ManyToOne 映射单向多对一关系，使用 fetch 属性可新修改默认关联属性的加载策略（LAZY为软加载）
    //@JoinColumn 映射外键
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
