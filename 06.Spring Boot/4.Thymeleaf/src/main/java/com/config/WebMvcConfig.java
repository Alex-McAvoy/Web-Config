package com.config;

import org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * MVC 配置
 * @author Alex McAvoy
 * @date 2022/9/22 14:05
 * @version 1.0
 **/
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /** 静态资源处理
     * @param registry 资源注册 Handler
     * @author Alex McAvoy
     * @date 2022/9/22 14:07
     **/
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/META-INF/resources/");
        super.addResourceHandlers(registry);
    }

    /** POST 请求伪装 PUT 与 DELETE 请求
     * @return org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter
     * @author Alex McAvoy
     * @date 2022/11/10 9:55
     **/
    @Bean
    public OrderedHiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new OrderedHiddenHttpMethodFilter();
    }
}
