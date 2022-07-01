package com.component.config;

import com.component.beans.Book;
import com.component.beans.Person;
import com.component.beans.Worker;
import com.component.condition.LinuxCondition;
import com.component.condition.WindowsCondition;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**配置类 @Configuration
 * 通知 Spring 该类为一配置类，可使用如下方法加载
 * ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
 * **/
@Configuration
/** 指定扫描包 @ComponentScan
 *  通过 value 属性指定要扫描的包，可扫描：@Repository、@Controller、@Service、@Component
 *  excludeFilters：排除规则，其值为一 Filter
 *  includeFilters：包含规则，其值为一 Filter
 * **/
@ComponentScan(value = "com.component", includeFilters = {
        @Filter(type = FilterType.ANNOTATION, value = {Controller.class, Service.class, Repository.class})
})
public class MainConfig {

    /** 注册 Bean @Bean
     * 给容器中注册一个 Bean，类型为返回值类型
     * 默认用方法名作 id，可使用 name 属性更改
    **/
    @Bean(name = "book01")
    public Book book() {
        return new Book("xxx", 20);
    }

    /** 设置组件的作用域 @Scope
     * singleton：单实例（默认）
     * prototype：多实例
     * request：同一请求创建一个实例
     * session：同一个session创建一个实例
     * **/
    @Scope("prototype")
    @Bean
    public Person person01(){
        return new Person("AA",500);
    }

    /** 懒加载 @Lazy
     *  对于单实例，默认在容器启动时创建对象
     *  懒加载：容器启动时不创建对象，第一次使用对象创建对象并初始化
     * **/
    @Lazy
    @Bean
    public Person person02() {
        System.out.println("向容器中添加 Person");
        return new Person("BB",23);
    }

    /** 条件注册 @Conditional
     *  按照一定条件进行判断，满足条件在容器中注册 Bean
     * **/
    @Conditional({WindowsCondition.class})
    @Bean
    public Worker worker01() {
        return new Worker("aa",32);
    }
    @Conditional({LinuxCondition.class})
    @Bean
    public Worker worker02() {
        return new Worker("bb",32);
    }
}
