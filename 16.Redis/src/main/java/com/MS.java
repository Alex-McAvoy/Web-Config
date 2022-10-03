package com;

import redis.clients.jedis.Jedis;

/**
 * @Description: Redis 主从复制
 * @Author: Alex McAvoy
 * @Date: 2022/10/3 14:08
 * @Version: 1.0
 **/
public class MS {
    public static void main(String[] args) {
        Jedis jedis_M = new Jedis("127.0.0.1",6379); //主机
        Jedis jedis_S = new Jedis("127.0.0.1",6380); //从机

        // 由于政治正确，slaveof 被迫更名为 replicaof，但 slaveof 仍可使用
//        jedis_S.slaveof("127.0.0.1",6379); //为从机设置主机
        jedis_S.replicaof("127.0.0.1",6379); //为从机设置主机

        jedis_M.set("class","123"); //主机写

        // 需要注意的是，由于内存要远快与硬盘
        // 因此在第一次设置key时可能会导致使用程序读取时读不出
        // 此时再执行一次即可
        String result = jedis_S.get("class"); //从机读
        System.out.println(result);


    }
}
