package com.seven;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.utils.RabbitMQUtils;

import java.util.Scanner;

/**
 * 交换机，生产者
 * @author Alex McAvoy
 * @date 2022/10/8 22:07
 * @version 1.0
 **/
public class EmitLog {

    public static final String FANOUT_EXCHANGE_NAME = "fanout_logs"; //扇出交换机名称
    public static final String DIRECT_EXCHANGE_NAME = "direct_logs"; //直接交换机名称
    public static final String TOPIC_EXCHANGE_NAME = "topic_logs"; //主题交换机名称

    /**
     * 交换机
     * - 作用：消息被传到队列后，只能被一个消费者使用，使用交换机，可以让一条消息被多个消费者使用
     * - 原理：
     *   - 通过 routingKey 来将队列绑定到一个交换机上
     *   - 由交换机负责将消息的传送到队列中
     *   - 每个队列对应消费者，从而让一条消息可被转发到多个队列上，以供多个消费者使用
     * - 模式：
     *   - fanout: 扇出，广播形式，即订阅/发布
     *   - direct: 直接，路由形式，其与扇出模式相比，不同的绑定队列的 routingKey 不同
     *   - topic: 主题，绑定队列时的 routingKey 是一单词列表，单词间用 . 隔开
     *     - 可使用 * 来替代一个单词，用 # 来替代零到多个单词
     *     - 当队列绑定键是 # 时，接收所有消息，该队列绑定类型为 fanout
     *     - 当队列绑定键没有 #、* 时，只接收符合匹配的消息，该队列绑定类型为 direct
     **/
    public static void main(String[] args) throws Exception {
		// 扇出模式，消费者为 ReceiveLogs01、ReceiveLogs02
//        fanoutExchange(); 
		// 直接模式，消费者为 ReceiveLogs03、ReceiveLogs04
//        directExchange(); 
		// 主题模式，消费者为 ReceiveLogs05、ReceiveLogs06
        topicExchange(); 
    }

    /**
     * 扇出模式，消费者为 ReceiveLogs01、ReceiveLogs02
     * @return void
     * @author Alex McAvoy
     * @date 2022/10/8 22:33
     **/
    public static void fanoutExchange() throws Exception {
        Channel channel = RabbitMQUtils.getChannel();
        channel.exchangeDeclare(FANOUT_EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        System.out.println("请输入消息: ");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.next();
            channel.basicPublish(FANOUT_EXCHANGE_NAME, "123456", null, message.getBytes());
            System.out.println("生产者发出消息: " + message);
        }
    }

    /**
     * 直接模式，消费者为 ReceiveLogs03、ReceiveLogs04
     * @return void
     * @author Alex McAvoy
     * @date 2022/10/9 14:26
     **/
    public static void directExchange() throws Exception {
        Channel channel = RabbitMQUtils.getChannel();
        channel.exchangeDeclare(DIRECT_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        System.out.println("请输入消息: ");
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        while (scanner.hasNext()) {
            String message = scanner.next();
            if (i % 2 == 0) {
                channel.basicPublish(DIRECT_EXCHANGE_NAME, "info", null, message.getBytes());
                channel.basicPublish(DIRECT_EXCHANGE_NAME, "success", null, message.getBytes());
            } else {
                channel.basicPublish(DIRECT_EXCHANGE_NAME, "error", null, message.getBytes());
            }
            System.out.println("生产者发出消息: " + message);
            i++;
        }
    }

    /**
     * 主题模式，消费者为 ReceiveLogs05、ReceiveLogs06
     * @return void
     * @author Alex McAvoy
     * @date 2022/10/9 14:37
     **/
    public static void topicExchange() throws Exception{
        Channel channel = RabbitMQUtils.getChannel();
        channel.exchangeDeclare(TOPIC_EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        //ReceiveLogs05、ReceiveLogs06 收到
        String message = "AA";
        channel.basicPublish(TOPIC_EXCHANGE_NAME, "quick.orange.rabbit", null, message.getBytes());
        System.out.println("生产者发出消息: " + message + "\n");

        //ReceiveLogs05、ReceiveLogs06 收到
        message = "BB";
        channel.basicPublish(TOPIC_EXCHANGE_NAME, "lazy.orange.elephant", null, message.getBytes());
        System.out.println("生产者发出消息: " + message + "\n");

        //ReceiveLogs05 收到
        message = "CC";
        channel.basicPublish(TOPIC_EXCHANGE_NAME, "quick.orange.fox", null, message.getBytes());
        System.out.println("生产者发出消息: " + message + "\n");

        //ReceiveLogs06 收到
        message = "DD";
        channel.basicPublish(TOPIC_EXCHANGE_NAME, "lazy.brown.fox", null, message.getBytes());
        System.out.println("生产者发出消息: " + message + "\n");

        //ReceiveLogs06 收到
        message = "EE";
        channel.basicPublish(TOPIC_EXCHANGE_NAME, "lazy.pink.rabbit", null, message.getBytes());
        System.out.println("生产者发出消息: " + message + "\n");

        //不满足任何绑定，丢弃
        message = "FF";
        channel.basicPublish(TOPIC_EXCHANGE_NAME, "quick.brown.fox", null, message.getBytes());
        System.out.println("生产者发出消息: " + message + "\n");

        //不满足任何绑定，丢弃
        message = "GG";
        channel.basicPublish(TOPIC_EXCHANGE_NAME, "quick.orange.male.rabbit", null, message.getBytes());
        System.out.println("生产者发出消息: " + message + "\n");

        //ReceiveLogs06 收到
        message = "HH";
        channel.basicPublish(TOPIC_EXCHANGE_NAME, "lazy.orange.male.rabbit", null, message.getBytes());
        System.out.println("生产者发出消息: " + message + "\n");

    }
}
