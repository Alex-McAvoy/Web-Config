package com;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/2/19 3:13
 * @Version: 1.0
 **/
@Entity
@Table(name = "users", schema = "hibernate_test", catalog = "")
public class TestUsersEntity {
    private int id;
    private String user;
    private String password;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestUsersEntity that = (TestUsersEntity) o;
        return id == that.id &&
                Objects.equals(user, that.user) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, password);
    }

    @Override
    public String toString() {
        return "TestUsersEntity{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
