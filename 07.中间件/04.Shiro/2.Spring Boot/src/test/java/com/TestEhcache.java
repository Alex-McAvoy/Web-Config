package com;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.Test;

import java.io.InputStream;

/**
 * 测试 Ehcache
 * @author Alex McAvoy
 * @date 2022/10/28 17:12
 * @version 1.0
 **/
public class TestEhcache {
    @Test
    public void testEhcache() {
        //获取编译目录下的资源的流对象
        InputStream input =
                TestEhcache.class.getClassLoader().getResourceAsStream("classpath:ehcache.xml");
        //获取EhCache的缓存管理对象
        CacheManager cacheManager = new CacheManager(input);
        //获取缓存对象
        Cache cache = cacheManager.getCache("TestCache");
        //创建缓存数据
        Element element = new Element("name", "Alex");
        //存入缓存
        cache.put(element);
        //从缓存中取出数据输出
        Element element1 = cache.get("name");
        System.out.println("缓存中的数据: " + element1.getObjectValue());
    }
}
