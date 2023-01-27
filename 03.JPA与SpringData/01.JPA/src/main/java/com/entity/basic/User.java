package com.entity.basic;

import javax.persistence.*;
import java.util.Date;

/**
 * 注解说明
 *
 * @Entity: 令类映射数据表
 * @Table: 说明映射数据表对应的数据表名
 * @Id: 声明主键
 * @GenerateValue: 标注主键的生成策略
 * @Column: 令属性映射数据表的一个字段，可设置字段的长度、是否为空等属性
 * @Basic: 对未标注的 getXxx() 方法，默认为该注解，其会自动映射到数据表中的 XXX 字段
 * @Transient: 若一个属性非数据库表的字段映射，加上该注解后 ORM 框架会忽略该属性
 * @Temporal: 映射日期、时间的属性时，调整精度
 **/
@Cacheable(true)
@Table(name = "users")
@Entity
public class User {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    @Column(name = "name")
    private String name;
    private String password;
    @Temporal(TemporalType.DATE)
    private Date birth;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Transient
    private String info;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    //工具方法，不需要映射为数据表的一列
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}