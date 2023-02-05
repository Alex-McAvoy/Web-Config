package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Nacos配置中心主启动类
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2023/2/4 18:09
 **/
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ConfigCenterMain3377 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3377.class, args);
    }
}
