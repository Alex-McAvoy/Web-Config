package com;

import com.bean.Employee;
import com.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

/**
 * Redis 基本使用测试
 * @author Alex McAvoy
 * @date 2022/10/6 18:10
 * @version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired(required = false)
    EmployeeService employeeService;

    @Autowired
    StringRedisTemplate stringRedisTemplate; // Redis 操作 k-v 都是字符串的

    @Autowired
    RedisTemplate redisTemplate; // Redis 操作 k-v 都是对象的

    @Autowired
    RedisTemplate<Object,Employee> employeeRedisTemplate; // Redis 序列化器


    /** 测试 Redis 的 String 类型，通过 opsForValue(String) 可进行操作
     * @author Alex McAvoy
     * @date 2022/11/10 22:17
     **/
    @Test
    public void testString() {
        //保存
        stringRedisTemplate.opsForValue().append("msg","hello");
        //读取
        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);
    }

    /** 测试 Redis 的 List 类型，通过 opsForList(List) 可进行操作
     * @author Alex McAvoy
     * @date 2022/10/6 18:17
     **/
    @Test
    public void testList() {
        //保存
        stringRedisTemplate.opsForList().leftPush("list","hello");
        stringRedisTemplate.opsForList().leftPush("list","world");
        //读取
        List<String> list = stringRedisTemplate.opsForList().range("list",0,-1);
        System.out.println(list);
    }


    /** 测试 Redis 的 Set 类型，通过 opsForSet(Set) 可进行操作
     * @author Alex McAvoy
     * @date 2022/11/10 22:18
     **/
    @Test
    public void testSet() {
        //保存
        stringRedisTemplate.opsForSet().add("set","123");
        stringRedisTemplate.opsForSet().add("set","123");
        stringRedisTemplate.opsForSet().add("set","456");
        stringRedisTemplate.opsForSet().add("set","789");
        //读取
        Set<String> set = stringRedisTemplate.opsForSet().members("set");
        System.out.println(set);
    }

    /** 测试 Redis 的 Hash 类型，通过 opsForHash(Hash) 可进行操作
     * @author Alex McAvoy
     * @date 2022/11/10 22:18
     **/
    @Test
    public void testHash() {
        //保存
        stringRedisTemplate.opsForHash().put("hash","k1","v1");
        stringRedisTemplate.opsForHash().put("hash","k2","v2");
        stringRedisTemplate.opsForHash().put("hash","k3","v3");
        //读取
        Object object = stringRedisTemplate.opsForHash().get("hash","k2");
        System.out.println(object);
    }


    /** 测试 Redis 的 ZSet 类型，通过 opsForZSet(ZSet) 可进行操作
     * @author Alex McAvoy
     * @date 2022/11/10 22:18
     **/
    @Test
    public void testZSet() {
        //保存
        stringRedisTemplate.opsForZSet().add("zset","v1",1);
        stringRedisTemplate.opsForZSet().add("zset","132",2);
        stringRedisTemplate.opsForZSet().add("zset","v1",3);
        stringRedisTemplate.opsForZSet().add("zset","123",4);
        //读取
        Set<String> zset = stringRedisTemplate.opsForZSet().range("zset",0,-1);
        System.out.println(zset);
    }
    

    /** 测试 Redis 保存对象
     * @author Alex McAvoy
     * @date 2022/11/10 22:19
     **/
    @Test
    public void testSaveObject() {
        Employee employee = employeeService.getEmployeeById(1);
        // redisTemplate 有默认序列化规则，采用 JDK 的序列化机制
        // 若想转为 JSON 序列化形式，需要编写 RedisConfig 类来进行设置
        employeeRedisTemplate.opsForValue().set("emp-01",employee);
    }
}
