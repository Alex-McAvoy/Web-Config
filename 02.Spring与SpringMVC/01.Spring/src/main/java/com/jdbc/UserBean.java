package com.jdbc;


/**
 * TODO
 * @author Alex McAvoy
 * @date 2022/2/26 3:33
 * @version 1.0
 **/
public class UserBean {
    private int id;
    private String user;
    private String password;

    public UserBean() {
    }

    public UserBean(int id, String user, String password) {
        this.id = id;
        this.user = user;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
