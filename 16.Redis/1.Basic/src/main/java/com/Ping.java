package com;

import redis.clients.jedis.Jedis;

/**
 * 连接 redis
 * @author Alex McAvoy
 * @date 2022/10/3 14:18
 * @version 1.0
 **/
public class Ping {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        System.out.println(jedis.ping());
    }
}
