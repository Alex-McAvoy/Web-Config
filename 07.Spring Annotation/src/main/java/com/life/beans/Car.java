package com.life.beans;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/6/27 22:02
 * @Version: 1.0
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
