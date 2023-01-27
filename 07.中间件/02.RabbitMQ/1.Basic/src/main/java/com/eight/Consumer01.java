package com.eight;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.utils.RabbitMQUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 死信消费者01，正常队列
 * @author Alex McAvoy
 * @date 2022/10/9 14:58
 * @version 1.0
 **/
public class Consumer01 {
    public static final String NORMAL_EXCHANGE_NAME = "normal_exchange";
    public static final String DEAD_EXCHANGE_NAME = "dead_exchange";

    public static final String NORMAL_QUEUE_NAME = "normal_queue";
    public static final String DEAD_QUEUE_NAME = "dead_queue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMQUtils.getChannel();
        //---------声明交换机---------
        channel.exchangeDeclare(DEAD_EXCHANGE_NAME, BuiltinExchangeType.DIRECT); //声明死信交换机
        channel.exchangeDeclare(NORMAL_EXCHANGE_NAME, BuiltinExchangeType.DIRECT); //声明普通交换机

        //---------声明队列---------
		//声明死信队列
        channel.queueDeclare(DEAD_QUEUE_NAME, false, false, false, null); 

        //设置声明队列参数
        Map<String, Object> map = new HashMap<>();
		//正常队列设置死信交换机
        map.put("x-dead-letter-exchange", DEAD_EXCHANGE_NAME); 
		//设置死信 routingKey
        map.put("x-dead-letter-routing-key", "dead"); 

        /**设置正常队列的长度，死信来源三**/
        map.put("x-max-length", 6);

		//声明普通队列
        channel.queueDeclare(NORMAL_QUEUE_NAME, false, false, false, map); 

        //---------绑定交换机与队列---------
		//普通交换机绑定普通队列
        channel.queueBind(NORMAL_QUEUE_NAME, NORMAL_EXCHANGE_NAME, "normal"); 
		//死信交换机绑定死信队列
        channel.queueBind(DEAD_QUEUE_NAME, DEAD_EXCHANGE_NAME, "dead"); 

        //---------等待接收消息---------
        System.out.println("Consumer01 等待接收消息");

        //---------接收消息---------
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            if (message.equals("info5")) {
                /**拒绝消息，死信来源一**/
                channel.basicReject(delivery.getEnvelope().getDeliveryTag(), false);
                System.out.println("Consumer01 接收到的消息：" + message + "，该消息被拒绝");
            } else {
                System.out.println("Consumer01 接收到的消息：" + message);
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };
        channel.basicConsume(NORMAL_QUEUE_NAME, false, deliverCallback, consumerTag -> {});
    }
}
