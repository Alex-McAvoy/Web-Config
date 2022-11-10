package com.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Druid 数据源配置
 * @author Alex McAvoy
 * @date 2022/9/29 13:47
 * @version 1.0
 **/
@Configuration
public class DruidConfig {

    /** 绑定数据源
     * @return javax.sql.DataSource
     * @author Alex McAvoy
     * @date 2022/9/29 13:53
     **/
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }

    /** Druid 后台管理的Servlet
     * @return org.springframework.boot.web.servlet.ServletRegistrationBean
     * @author Alex McAvoy
     * @date 2022/9/29 13:49
     **/
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String,String> initParams = new HashMap<>(5);

        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");
        //默认允许所有访问
        initParams.put("allow","");
        //拒绝访问
//        initParams.put("deny","192.168.15.21");

        bean.setInitParameters(initParams);
        return bean;
    }

    /** web监控的filter
     * @return org.springframework.boot.web.servlet.FilterRegistrationBean<com.alibaba.druid.support.http.WebStatFilter>
     * @author Alex McAvoy
     * @date 2022/9/29 13:49
     **/
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter(){
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());

        Map<String,String> initParams = new HashMap<>(5);
        initParams.put("exclusions","*.js,*.css,/druid/*");

        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Collections.singletonList("/*"));

        return bean;
    }
}
