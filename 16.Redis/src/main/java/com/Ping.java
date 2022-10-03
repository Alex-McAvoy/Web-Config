package com;

import redis.clients.jedis.Jedis;

/**
 * @Description: 连接 redis
 * @Author: Alex McAvoy
 * @Date: 2022/10/3 13:41
 * @Version: 1.0
 **/
public class Ping {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        System.out.println(jedis.ping());
    }
}
