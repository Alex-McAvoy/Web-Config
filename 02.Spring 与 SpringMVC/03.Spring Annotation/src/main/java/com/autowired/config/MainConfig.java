package com.autowired.config;

import com.autowired.beans.Cup;
import com.autowired.dao.CupDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/** 自动装配
 *  1. @Autowired 自动注入组件
 *   - 默认按照类型优先去容器中寻找对应的组件
 *   - 若找到多个相同类型的组件，会再按照属性名作为组件的 id(属性名) 去容器中查找
 *   - 可使用 @Qualifier 来明确指定要装配的组件的 id（见 CupService）
 *   - 在 Spring 自动装配时，可以使用 @Primary 来指定默认首选装配的组件，要求此时不能使用 @Qualifier 来指定
 *  2. @Autowired 自动注入构造器、参数、set方法（见Boss）
 *   - 标注在set方法上，Spring 创建对象时，就会调用方法完成赋值，方法使用的参数会从 IOC 中获取
 *   - 标注在构造器上，Spring 创建对象时，就会调用构造器，方法使用的参数会从 IOC 中获取
 *   - 标注在参数上，Spring 创建对象时，就会调用构造器，方法使用的参数会从 IOC 中获取
 *   但三者仅可被同时标注一个，标注两个及以上时，会被覆盖
 * **/
@ComponentScan({"com.autowired.service", "com.autowired.dao",
        "com.autowired.controller","com.autowired.beans"})
@Configuration
public class MainConfig {
    @Bean(name = "cupDao2")
    public CupDao cupDao2() {
        CupDao cupDao = new CupDao();
        cupDao.setLabel("2");
        return cupDao;
    }

    @Primary
    @Bean(name = "cupDao3")
    public CupDao cupDao3() {
        CupDao cupDao = new CupDao();
        cupDao.setLabel("3");
        return cupDao;
    }
}
