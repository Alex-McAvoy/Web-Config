package com.eight;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.utils.RabbitMQUtils;

/**
 * @Description: 死信生产者
 * @Author: Alex McAvoy
 * @Date: 2022/10/9 14:54
 * @Version: 1.0
 **/
public class Producer {
    public static final String NORMAL_EXCHANGE_NAME = "normal_exchange";
    public static final String DEAD_EXCHANGE_NAME = "dead_exchange";

    public static final String NORMAL_QUEUE_NAME = "normal_queue";
    public static final String DEAD_QUEUE_NAME = "dead_queue";

    /** 死信
     *   - 死亡的消息，一般会被队列丢弃
     *   - 可通过使用 DLX（Dead Letter Exchange）来将死信存到指定队列
     *   - 产生原因：消息被拒绝、消息 TTL 过期、队列达到最大长度
     * **/
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtils.getChannel();

        /**设置 TTL 时间，单位 ms，死信来源二**/
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().expiration("10000").build();

        for (int i = 0; i < 10; i++) {
            String message = "info" + i;
            channel.basicPublish(NORMAL_EXCHANGE_NAME,"normal",properties,message.getBytes());
        }
    }
}
