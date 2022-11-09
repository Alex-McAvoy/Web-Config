package com.bean;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ConfigurationProperties(prefix = "test"): 将本类中的所有属性与配置文件中相关配置进行绑定
 * prefix：配置文件中哪个属性与该类进行映射
 * @Component: 标明该类是一个组件，只有组件可以使用 @ConfigurationProperties
 * @Validated: @ConfigurationProperties 支持JSR303校验
 * @PropertySource: @ConfigurationProperties默认加载全局配置文件 application.yml，使用该注解可指定加载的配置文件
 **/
@Component
@ConfigurationProperties(prefix = "testbean")
@PropertySource(value = {"classpath:config/testBean.properties"})
public class TestBean {
    /**
     * 除 @ConfigurationProperties 外，可以使用 @Value 注解，以在业务逻辑中获取配置文件中的某项值
     * 其与 <bean class="class"><property name="name" value="xxx"></property></bean> 标签中的value类似
     * 在 Spring 中的配置文件中，使用 <bean></bean> 可从环境变量、配置文件、#{SpeL}中获取值 @Value 具有同样功能
     * 另 @Value 不支持 JSR303 校验、不支持 map、list 等复杂类型封装
     */
//    @Value("${testbean.name}")
    private String name;
    //    @Value("#{11*2}")
    private Integer age;
    //    @Value("true")
    private Boolean boss;
    private Date birth;

    private Map<String, Object> maps;
    private List<Object> lists;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getBoss() {
        return boss;
    }

    public void setBoss(Boolean boss) {
        this.boss = boss;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birth=" + birth +
                ", maps=" + maps +
                ", lists=" + lists +
                '}';
    }
}
