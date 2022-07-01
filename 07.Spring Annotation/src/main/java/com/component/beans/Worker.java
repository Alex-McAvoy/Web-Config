package com.component.beans;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/6/27 1:00
 * @Version: 1.0
 **/
public class Worker {
    private String name;
    private Integer age;

    public Worker() {
    }

    public Worker(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "worker{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
