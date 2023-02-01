package com.config;

import com.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * Spring Security 配置类
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2022/10/31 14:12
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    /**
     * 设置登录拦截，具体由 UserDetailsServiceImpl 实现
     *
     * @param auth 权限管理构造器
     * @author Alex McAvoy
     * @date 2022/11/3 21:27
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(password());
    }

    /**
     * 注入密码加密类
     *
     * @return org.springframework.security.crypto.password.PasswordEncoder
     * @author Alex McAvoy
     * @date 2022/11/3 21:28
     **/
    @Bean
    protected PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置实习 Remember Me 功能的数据源
     *
     * @return org.springframework.security.web.authentication.rememberme.PersistentTokenRepository
     * @author Alex McAvoy
     * @date 2022/11/9 15:39
     **/
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //创建数据表
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    /**
     * 自定义登录规则
     *
     * @param httpSecurity httpSecurity
     * @author Alex McAvoy
     * @date 2022/11/3 21:34
     **/
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        //登录拦截器
        httpSecurity.formLogin()
                //自定义登录页
                .loginPage("/user/login")
                //登录成功后跳转路径
                .defaultSuccessUrl("/user/index").permitAll();

        //请求拦截器
        httpSecurity.authorizeRequests()
                //设置哪些路径可以直接访问不需要认证
                .antMatchers("/", "/user/hello", "/user/login").permitAll()
                //1.具有单个权限 admin 可访问设定的路径
                //.antMatchers("/user/index").hasAuthority("admin")
                //2.具有多个权限 admin、manager 中的任一个即可访问设定的路径
                //.antMatchers("/user/index").hasAnyAuthority("admin,manager")
                //3.具有单个角色 sale 可访问设定的路径，其会将角色加前缀 ROLE_，因此在 UserDetailsServiceImpl 设置角色时，需要加上 ROLE_ 前缀
                //.antMatchers("/user/index").hasRole("sale")
                //4.具有多个角色 role1、role2 中的任一个即可访问设定的路径，其会将角色加前缀 ROLE_，因此在 UserDetailsServiceImpl 设置角色时，需要加上 ROLE_ 前缀
                //.antMatchers("/user/index").hasAnyRole("role1,role2")
                //设置除不需认证的路径外，拦截请求
                .anyRequest().authenticated();

        //配置退出账户
        httpSecurity.logout().logoutUrl("/logout").logoutSuccessUrl("/test/hello").permitAll();

        //配置无权限访问跳转页面
        httpSecurity.exceptionHandling().accessDeniedPage("/unauth.html");

        //设置Remember Me
        httpSecurity.rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(300).userDetailsService(userDetailsService);

        //关闭CSRF防护
//        httpSecurity.csrf().disable();
    }
}
