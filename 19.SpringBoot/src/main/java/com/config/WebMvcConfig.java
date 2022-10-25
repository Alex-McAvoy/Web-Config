package com.config;

//import com.component.LoginHandlerInterceptor;
import com.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter;

/**
 * @Description: MVC 配置
 * @Author: Alex McAvoy
 * @Date: 2022/9/22 14:05
 * @Version: 1.0
 **/

/**
 * @Configuration: 指明当前类是一个配置类，替代之前的 Spring 配置文件
 * @EnableWebMvc: 全面接管 SpringBoot 的 MVC 配置，原有的 MVC 自动配置失效，该方案一般不启用
 * WebMvcConfigurationSupport: 既保留了 SpringBoot 的 MVC 自动配置，也可对 MVC 配置进行自定义扩展
 * 在继承 WebMvcConfigurationSupport 后，以下功能会失效，需要重新配置：
 *  1.用于静态资源处理的 ResourceHandlerRegistry
 *  2.用于 POST 请求伪装 PUT 与 DELETE 请求的 HiddenHttpMethodFilter
 */
//@EnableWebMvc
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * @Description: 静态资源处理
     * @Param: [registry]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/9/22 14:07
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

    /**
     * @Description: POST 请求伪装 PUT 与 DELETE 请求
     * @Param: []
     * @Return: org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter
     * @Author: Alex McAvoy
     * @Date: 2022/9/22 14:12
     **/
    @Bean
    public OrderedHiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new OrderedHiddenHttpMethodFilter();
    }

    /**
     * @Description: MVC 映射处理
     * @Param: [registry]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/9/22 17:03
     **/
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /** addViewController: 访问的 url 地址
         *  setViewName: Controller 中 Mapping 映射的地址
         * */
        //映射登录主页
        registry.addViewController("/").setViewName("/login");
        registry.addViewController("/index.html").setViewName("/login");
        //映射登录成功页，防止表单重复提交
        registry.addViewController("/main.html").setViewName("/main");
    }

    /**
     * @Description: 注册拦截器
     * @Param: [registry]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/9/22 17:09
     **/
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).
                addPathPatterns("/**"). //拦截所有
                excludePathPatterns("/index.html","/","/login"); //排除登录
    }
}
