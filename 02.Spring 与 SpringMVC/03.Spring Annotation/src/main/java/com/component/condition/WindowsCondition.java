package com.component.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 判断系统是否为 Windows 系统
 * @author Alex McAvoy
 * @date 2022/6/27 0:48
 * @version 1.0
 **/
public class WindowsCondition implements Condition {

    /**
     * 匹配条件
     * @Param: [conditionContext：判断条件使用上下文, annotatedTypeMetadata：注释信息]
     * @Return: boolean
     * @author Alex McAvoy
     * @date 2022/6/27 0:50
     **/
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Environment environment = conditionContext.getEnvironment();
        String property = environment.getProperty("os.name");
        if(property.contains("Windows")){
            return true;
        }
        return false;
    }

}
