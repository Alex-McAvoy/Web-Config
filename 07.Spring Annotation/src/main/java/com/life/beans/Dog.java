package com.life.beans;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * TODO
 * @author Alex McAvoy
 * @date 2022/6/27 22:19
 * @version 1.0
 **/
@Component
public class Dog {
    public Dog() {
        System.out.println("Dog 构造器");
    }

    @PostConstruct
    public void init() {
        System.out.println("Dog 初始化");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Dog 销毁");
    }
}
