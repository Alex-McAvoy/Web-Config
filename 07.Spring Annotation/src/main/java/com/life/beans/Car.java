package com.life.beans;

/**
 * TODO
 * @author Alex McAvoy
 * @date 2022/6/27 22:02
 * @version 1.0
 **/
public class Car {
    public Car() {
        System.out.println("Car 构造器");
    }
    public void init() {
        System.out.println("Car 初始化");
    }
    public void destroy() {
        System.out.println("Car 销毁");
    }
}
