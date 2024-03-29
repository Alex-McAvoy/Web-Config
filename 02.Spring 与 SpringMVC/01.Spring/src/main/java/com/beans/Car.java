package com.beans;

/**
 * TODO
 * @author Alex McAvoy
 * @date 2022/2/23 0:37
 * @version 1.0
 **/
public class Car {
    private int id;
    private String name;
    private double width;

    public Car() {
    }

    public Car(int id, String name, double width) {
        this.id = id;
        this.name = name;
        this.width = width;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", width=" + width +
                '}';
    }
}
