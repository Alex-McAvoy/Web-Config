package com.life.config;


import com.life.beans.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("com.life.beans")
@Configuration
public class MainConfig {

    /** Bean 的初始化与销毁方法
     *  容器管理 Bean 的生命周期：创建->初始化->使用->销毁
     *  1. 在 @Bean 注解中，使用 initMethod、destroyMethod 属性来指定 Bean 中的方法作为初始化与销毁方法（Car类）
     *     - 初始化：对象创建完成，并赋值好，会调用初始化方法
     *     - 销毁：
     *      - 单实例：容器关闭时销毁
     *      - 多实例：容器不会管理这个 Bean，即不会调用销毁方法，需要手动调用
     *  2. 在 Bean 的实体类中，实现 InitializingBean 与 DisposableBean 这两个接口的方法（Cat 类）
     *  3. 在 Bean 的实体类中，使用 JSR250 所提供的 @PostConstruct、@PreDestroy 注解（Dog 类）
     *     - @PostConstruct：在 Bean 创建完成且属性赋值完成后，执行初始化方法
     *     - @PreDestroy：在容器销毁 Bean 前通知进行销毁
     * **/
    @Bean(initMethod = "init",destroyMethod = "destroy")
    public Car car() {
        return new Car();
    }
}
