package com.mybatis.bean.secondcache;

import java.io.Serializable;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/3/30 16:08
 * @Version: 1.0
 **/
//若想使用二级缓存，POJO 需要实现序列化接口Serializable
public class Man implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;;
    private String gender;
    private String email;

    public Man() {
    }

    public Man(Integer id, String name, String gender, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Man{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
