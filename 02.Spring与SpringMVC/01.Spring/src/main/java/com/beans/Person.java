package com.beans;

/**
 * TODO
 * @author Alex McAvoy
 * @date 2022/2/23 0:21
 * @version 1.0
 **/
public class Person {
    private int id;
    private String name;
    private int age;
    private Car car;

    public Person() {
    }

    public Person(int id, String name, int age, Car car) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.car = car;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", car=" + car +
                '}';
    }
}
