package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger 生成文档配置类
 * @author Alex McAvoy
 * @date 2022/10/10 11:24
 * @version 1.0
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2).
                groupName("webApi").
                apiInfo(webApiInfo()).
                select().build();
    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder().
                title("RabbitMQ 接口文档").
                description("该文档描述了 RabbitMQ 微服务接口定义").
                version("1.0").
                contact(new Contact("root","127.0.0.1","root@qq.com")).
                build();
    }
}
