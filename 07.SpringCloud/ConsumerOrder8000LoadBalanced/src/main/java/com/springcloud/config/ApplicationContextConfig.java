package com.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 应用程序上下文配置
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2023/1/31 16:18
 **/
@Configuration
public class ApplicationContextConfig {
    /** 在应用中调用Rest服务
     * SpringCloud 2020.0.x后，原有的Ribbon被代替为LoadBalanced
     * 若需对提供服务的模块进行负载均衡，需加@LoadBalanced注解
     * @return org.springframework.web.client.RestTemplate
     * @author Alex McAvoy
     * @date 2023/1/31 20:02
     **/
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
