package com.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis 连接池工具（单例模式实现）
 * @author Alex McAvoy
 * @date 2022/10/3 14:18
 * @version 1.0
 **/
public class JedisPoolUtil {
    private static volatile JedisPool jedisPool = null;

    private JedisPoolUtil() {
    }

    public static JedisPool getJedisPoolInstance() {
        if (jedisPool == null) {
            //同步块
            synchronized (JedisPoolUtil.class) {
                if (jedisPool == null) {
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    //设置最大连接数
                    poolConfig.setMaxTotal(1000);
                    //设置空间连接
                    poolConfig.setMaxIdle(32);
                    //设置最大阻塞时间
//                    poolConfig.setMaxWait(1000000);
                    poolConfig.setTestOnBorrow(true);

                    jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);
                }
            }
        }
        return jedisPool;
    }

    public static void release(JedisPool jedisPool, Jedis jedis) {
        if (null != jedis) {
            jedisPool.returnResource(jedis);
        }
    }
}
