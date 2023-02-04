package com.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenFegin日志配置
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2023/2/1 13:13
 **/
@Configuration
public class OpenFeginConfig {
    /** 日志级别：
     * - NONE：默认，不显示任何日志
     * - BASIC：仅记录请求方法、URL、响应码、执行时间
     * - HEADERS：除BASIC中定义的信息外，还有请求、响应的头信息
     * - FULL：除HEADERS中定义信息外，还有请求、响应的正文与元数据
     **/
    @Bean
    Logger.Level feginLoggerLevel() {
        return Logger.Level.FULL;
    }
}
