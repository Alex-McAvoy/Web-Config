package com.component.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Description: 判断系统是否为 Linux 系统
 * @Author: Alex McAvoy
 * @Date: 2022/6/27 0:48
 * @Version: 1.0
 **/
public class LinuxCondition implements Condition {
    /**
     * @Description: 匹配条件
     * @Param: [conditionContext：判断条件使用上下文, annotatedTypeMetadata：注释信息]
     * @Return: boolean
     * @Author: Alex McAvoy
     * @Date: 2022/6/27 0:50
     **/
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        //1、获取到 ioc 使用的 BeanFactory
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
        //2、获取类加载器
        ClassLoader classLoader = conditionContext.getClassLoader();
        //3、获取当前环境信息
        Environment environment = conditionContext.getEnvironment();
        //4、获取到 Bean 定义的注册类
        BeanDefinitionRegistry registry = conditionContext.getRegistry();

        String property = environment.getProperty("os.name");

        //可以判断容器中的bean注册情况，也可以给容器中注册bean
        boolean definition = registry.containsBeanDefinition("worker02");
        if(property.contains("linux")){
            return true;
        }

        return false;
    }
}
