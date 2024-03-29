package com.config;

import com.bean.Department;
import com.bean.Employee;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.net.UnknownHostException;
import java.time.Duration;

/**
 * Redis 的 config
 * - 在引入 Redis starter 后，CacheManager 中保存的是 RedisCacheManager
 *   其会自动创建 RedisCache 来作为缓存组件，通过操作 Redis 来缓存数据
 * - 默认情况下，创建的是 RedisTemplate<Object,Object>
 *   同时要求相应的 Bean 继承 JDK 的 Serializable 接口以实现序列化
 * - 但有时需要存储对象以 Json 数据的形式存储，这就需要自定义 CacheManager
 * @author Alex McAvoy
 * @date 2022/10/6 18:36
 * @version 1.0
 **/
@Configuration
public class RedisConfig {


    /** 创建 Redis 缓存管理器
     * - @Primary: 设为默认缓存管理器
     * @param redisConnectionFactory redis 连接工厂
     * @return org.springframework.data.redis.cache.RedisCacheManager
     * @author Alex McAvoy
     * @date 2022/10/6 20:16
     **/
    @Primary
    @Bean
    RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        //Redis 序列化器
		RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        //Json 序列化器
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        // 解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        // 配置 Json 序列化，解决乱码问题
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                //过期时间0秒
				.entryTtl(Duration.ZERO)
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                .disableCachingNullValues();

        // 返回 Redis 缓存管理器
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(config)
                .build();
    }

    /** 获取所有员工的缓存 key 生成器，keyGenerator
     * @return org.springframework.cache.interceptor.KeyGenerator
     * @author Alex McAvoy
     * @date 2022/10/6 21:05
     **/
    @Bean("employeesKeyGenerator")
    public KeyGenerator employeesKeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                return "employees";
            }
        };
    }

    /** 获取所有部门的缓存 key 生成器，keyGenerator
     * @return org.springframework.cache.interceptor.KeyGenerator
     * @author Alex McAvoy
     * @date 2022/10/6 21:05
     **/
    @Bean("departmentsKeyGenerator")
    public KeyGenerator departmentsKeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                return "departments";
            }
        };
    }

    /** Employee 类的 RedisTemplate，当对 Employee 对象进行 RedisTemplate 操作时，改为使用该类即可
     * @param redisConnectionFactory
     * @return org.springframework.data.redis.core.RedisTemplate<java.lang.Object,com.bean.Employee>
     * @author Alex McAvoy
     * @date 2022/10/6 19:54
     **/
    @Bean
    public RedisTemplate<Object, Employee> employeeRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Employee> template = new RedisTemplate<Object, Employee>();
        template.setConnectionFactory(redisConnectionFactory);
        // 获取 Json 序列化器
        Jackson2JsonRedisSerializer<Employee> serializer = new Jackson2JsonRedisSerializer<Employee>(Employee.class);
        // 修改默认序列化规则
        template.setDefaultSerializer(serializer);
        return template;
    }

    /** Department 类的 RedisTemplate，当对 Department 对象进行 RedisTemplate 操作时，改为使用该类即可
     * @param redisConnectionFactory redis 连接工厂
     * @return org.springframework.data.redis.core.RedisTemplate<java.lang.Object,com.bean.Department>
     * @author Alex McAvoy
     * @date 2022/10/6 20:24
     **/
    @Bean
    public RedisTemplate<Object, Department> departmentRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Department> template = new RedisTemplate<Object, Department>();
        template.setConnectionFactory(redisConnectionFactory);
        // 获取 Json 序列化器
        Jackson2JsonRedisSerializer<Department> serializer = new Jackson2JsonRedisSerializer<Department>(Department.class);
        // 修改默认序列化规则
        template.setDefaultSerializer(serializer);
        return template;
    }
}
