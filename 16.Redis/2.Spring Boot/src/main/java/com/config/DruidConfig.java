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
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Druid 数据源配置
 * @Author: Alex McAvoy
 * @Date: 2022/9/29 13:47
 * @Version: 1.0
 **/
@Configuration
public class DruidConfig {
    /**
     * @Description: 绑定数据源
     * @Param: []
     * @Return: javax.sql.DataSource
     * @Author: Alex McAvoy
     * @Date: 2022/9/29 13:53
     **/
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }
    
    /**
     * @Description: 管理后台的Servlet
     * @Param: []
     * @Return: org.springframework.boot.web.servlet.ServletRegistrationBean
     * @Author: Alex McAvoy
     * @Date: 2022/9/29 13:49
     **/
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String> initParams = new HashMap<>();

        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");
        initParams.put("allow",""); //默认就是允许所有访问
//        initParams.put("deny","192.168.15.21"); //拒绝访问

        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * @Description: web监控的filter
     * @Param: []
     * @Return: org.springframework.boot.web.servlet.FilterRegistrationBean
     * @Author: Alex McAvoy
     * @Date: 2022/9/29 13:49
     **/
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");

        bean.setInitParameters(initParams);

        bean.setUrlPatterns(Arrays.asList("/*"));

        return bean;
    }
}
