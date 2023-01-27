package com;

import redis.clients.jedis.Jedis;

/**
 * Redis 主从复制
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2022/10/3 14:18
 **/
public class MS {
    public static void main(String[] args) {
        //主机
        Jedis jedis_M = new Jedis("127.0.0.1", 6379);
        //从机
        Jedis jedis_S = new Jedis("127.0.0.1", 6380);

        // 由于政治正确，slaveof 被迫更名为 replicaof，但 slaveof 仍可使用
        //为从机设置主机
//        jedis_S.slaveof("127.0.0.1",6379);
        jedis_S.replicaof("127.0.0.1", 6379);

        //主机写
        jedis_M.set("class", "123");

        // 需要注意的是，由于内存要远快与硬盘
        // 因此在第一次设置key时可能会导致使用程序读取时读不出
        // 此时再执行一次即可

        //从机读
        String result = jedis_S.get("class");
        System.out.println(result);
    }
}
