package com;

import com.utils.JedisPoolUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Description: Redis 连接池
 * @Author: Alex McAvoy
 * @Date: 2022/10/3 14:28
 * @Version: 1.0
 **/
public class Pool {
    public static void main(String[] args) {
        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
        JedisPool jedisPool2 = JedisPoolUtil.getJedisPoolInstance();

        System.out.println(jedisPool == jedisPool2);

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set("aa","bb");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JedisPoolUtil.release(jedisPool, jedis);
        }
    }
}
