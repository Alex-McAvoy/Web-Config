package com.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 测试 Bean
 * - @ConfigurationProperties(prefix = "test"): 将本类中的所有属性与配置文件中相关配置进行绑定
 *   其中，prefix 指定配置文件中哪个属性与该类进行映射
 * - @Component: 标明该类是一个组件，只有组件可以使用 @ConfigurationProperties
 * - @Validated: @ConfigurationProperties 支持JSR303校验
 * - @PropertySource: @ConfigurationProperties默认加载全局配置文件 application.yml，使用该注解可指定加载的配置文件
 * @author Alex McAvoy
 * @date 2022/9/16 21:58
 * @version 1.0
 **/
@Component
@ConfigurationProperties(prefix = "user")
@PropertySource(value = {"classpath:config/user.properties"})
@Data
public class User {
    /**
     * 除 @ConfigurationProperties 外，可以使用 @Value 注解，以在业务逻辑中获取配置文件中的某项值
     * 其与 <bean class="class"><property name="name" value="xxx"></property></bean> 标签中的value类似
     * 在 Spring 中的配置文件中，使用 <bean></bean> 可从环境变量、配置文件、#{SpeL}中获取值 @Value 具有同样功能
     * 另 @Value 不支持 JSR303 校验、不支持 map、list 等复杂类型封装
     */
    @Value("${user.name}")
    private String name;
    @Value("#{11*2}")
    private Integer age;
    @Value("true")
    private Boolean boss;
    private Date birth;

    private Map<String, Object> maps;
    private List<Object> lists;

}
