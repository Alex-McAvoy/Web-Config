package com.beans;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/2/22 23:59
 * @Version: 1.0
 **/
public class Hello {
    private String name;

    public Hello() {
    }

    public Hello(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void hello() {
        System.out.println("hello:" + name);
    }
}
