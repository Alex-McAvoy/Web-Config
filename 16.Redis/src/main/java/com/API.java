package com;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @Description: Redis 常用 API
 * @Author: Alex McAvoy
 * @Date: 2022/10/3 13:47
 * @Version: 1.0
 **/
public class API {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);

        jedis.set("k1","v1");
        jedis.set("k2","v2");
        jedis.set("k3","v3");

        System.out.println(jedis.get("k1"));

        Set<String> sets = jedis.keys("*");
        System.out.println(sets);
    }
}
