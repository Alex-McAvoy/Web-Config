package com.aop.config;

import com.aop.beans.LogAspects;
import com.aop.beans.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy //开启基于注解的AOP模式
@Configuration
public class MainConfig {

    //业务逻辑类加入容器
    @Bean
    public MathCalculator calculator() {
        return new MathCalculator();
    }

    //切面类加入容器
    @Bean
    public LogAspects logAspects() {
        return new LogAspects();
    }
}
