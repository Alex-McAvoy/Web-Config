package com.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.realm.MyRealm;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

/**
 * Shiro 配置类
 * @author Alex McAvoy
 * @date 2022/10/28 22:40
 * @version 1.0
 **/
@Configuration
public class ShiroConfig {
    @Autowired
    private MyRealm myRealm;

    /**
     * 配置 SecurityManager
     * @return DefaultWebSecurityManager
     * @author Alex McAvoy
     * @date 2022/10/28 22:41
     **/
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        //创建 defaultWebSecurityManager 对象
        DefaultWebSecurityManager defaultWebSecurityManager = new
                DefaultWebSecurityManager();

        //md5加密
        HashedCredentialsMatcher matcher = new
                HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(3);

        //将加密对象存入 myRealm
        myRealm.setCredentialsMatcher(matcher);
        //将 myRealm 存入 defaultWebSecurityManager
        defaultWebSecurityManager.setRealm(myRealm);
        //设置RememberME
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager());
        //设置缓存管理器
        defaultWebSecurityManager.setCacheManager(getEhCacheManager());

        return defaultWebSecurityManager;
    }

    /**
     * Cookie 设置
     * @return org.apache.shiro.web.servlet.SimpleCookie
     * @author Alex McAvoy
     * @date 2022/10/29 12:23
     **/
    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        //设置跨域
        //cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(30 * 24 * 60 * 60);
        return cookie;
    }

    /**
     * Cookie 管理对象
     * @return org.apache.shiro.web.mgt.CookieRememberMeManager
     * @author Alex McAvoy
     * @date 2022/10/29 12:24
     **/
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new
                CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey("1234567890987654".getBytes());
        return cookieRememberMeManager;
    }

    /**
     * 缓存管理器
     * @return org.apache.shiro.cache.ehcache.EhCacheManager
     * @author Alex McAvoy
     * @date 2022/10/29 14:45
     **/
    public EhCacheManager getEhCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        InputStream is = null;
        try {
            is = ResourceUtils.getInputStreamForPath("classpath:ehcache.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        CacheManager cacheManager = new CacheManager(is);
        ehCacheManager.setCacheManager(cacheManager);
        return ehCacheManager;
    }

    /**
     * 配置 Shiro 内置过滤器拦截范围
     * @return DefaultShiroFilterChainDefinition
     * @author Alex McAvoy
     * @date 2022/10/28 22:43
     **/
    @Bean
    public DefaultShiroFilterChainDefinition
    shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new
                DefaultShiroFilterChainDefinition();
        //设置不认证可以访问的资源
        definition.addPathDefinition("/mainController/userLogin", "anon");
        definition.addPathDefinition("/mainController/login", "anon");
        //配置登出过滤器
        definition.addPathDefinition("/logout", "logout");
        //设置需要进行登录认证的拦截范围
        definition.addPathDefinition("/**", "authc");
        //添加存在用户的过滤器 rememberMe
        definition.addPathDefinition("/**", "user");
        return definition;
    }

    /**
     * 解析 thymeleaf 中的 shiro:相关属性
     * @return at.pollux.thymeleaf.shiro.dialect.ShiroDialect
     * @author Alex McAvoy
     * @date 2022/10/29 14:41
     **/
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
