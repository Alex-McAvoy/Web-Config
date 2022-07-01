package com.life.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/6/27 22:13
 * @Version: 1.0
 **/
@Component
public class Cat implements InitializingBean, DisposableBean {
    public Cat() {
        System.out.println("Cat 构造器");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Cat 初始化");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Cat 销毁");
    }

}
