package com.property.config;

import com.property.beans.Man;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MainConfig {

    /** 属性赋值
     *  对于未赋值的 Bean，可在相应的实体类中使用 @Value 进行赋值
     *  在 @Value 中，可以写如下内容：
     *  1. 基本数值、字符串
     *  2. SpEL，#{}
     *  3. 运行环境中的配置文件值，${}，需在配置类中结合 @PropertySource 注解引入配置文件
     **/
    @Bean
    public Man man(){
        return new Man();
    }
}
