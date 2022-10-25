package com.six;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.MessageProperties;
import com.utils.RabbitMQUtils;

import java.util.Scanner;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @Description: 发布确认生产者
 * @Author: Alex McAvoy
 * @Date: 2022/10/8 20:19
 * @Version: 1.0
 **/
public class ConfirmMessage {

    /**
     * 发布确认
     * - 将信道设为 confirm 模式，一旦消息被投递到匹配的消息队列后，会发送一个确认给生产者
     * - 若消息、队列是持久化的，那么确认消息会在消息写入磁盘后发出
     * - 分为单个确认、批量确认、异步确认三种模式
     * - 单个确认：发送一条消息确认一次，当出错时可以确认哪条消息出错，耗时长
     * - 批量确认：发送一批消息确认一次，当出错时无法确认哪条消息出错，只能确认某一批出错，耗时短
     * - 异步确认：结合单个确认和批量确认的优点，但编码复杂
     **/
    public static void main(String[] args) throws Exception {
//        publishMessageIndividually(); //单个确认，耗时：696ms
//        publishMessageBatch(); //批量确认，耗时：96ms
        publishMessageAsync(); //异步确认，耗时：106ms
    }

    private static final int MESSAGE_COUNT = 1000; //批量发送消息数
    private static final String INDIVIDUAL_QUEUE_NAME = "individual_confirm";
    private static final String BATCH_QUEUE_NAME = "batch_confirm";
    private static final String ASYNC_QUEUE_NAME = "async_confirm";

    /**
     * @Description: 单个确认
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/8 20:32
     **/
    public static void publishMessageIndividually() throws Exception {
        Channel channel = RabbitMQUtils.getChannel();
        channel.queueDeclare(INDIVIDUAL_QUEUE_NAME, false, false, false, null);
        channel.confirmSelect(); // 开启 confirm 模式

        long begin = System.currentTimeMillis(); //开始时间
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", INDIVIDUAL_QUEUE_NAME, null, message.getBytes());
            boolean flag = channel.waitForConfirms(); //单个消息发布后立刻确认
            if (flag) {
                System.out.println("消息发送成功");
            }
        }
        long end = System.currentTimeMillis(); //结束时间

        System.out.println("发布" + MESSAGE_COUNT + "个单独确认消息，耗时：" + (end - begin) + "ms");
    }

    /**
     * @Description: 批量确认
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/8 20:36
     **/
    public static void publishMessageBatch() throws Exception {
        Channel channel = RabbitMQUtils.getChannel();
        channel.queueDeclare(BATCH_QUEUE_NAME, false, false, false, null);
        channel.confirmSelect(); // 开启 confirm 模式

        long begin = System.currentTimeMillis(); //开始时间
        int batchSize = 100; // 批量确认长度
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", BATCH_QUEUE_NAME, null, message.getBytes());
            if (i % batchSize == 0) { //达到100条消息时，批量确认一次
                channel.waitForConfirms();
            }
        }
        long end = System.currentTimeMillis(); //结束时间

        System.out.println("发布" + MESSAGE_COUNT + "个批量确认消息，耗时：" + (end - begin) + "ms");
    }

    /**
     * @Description: 异步确认
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/8 20:50
     **/
    public static void publishMessageAsync() throws Exception {
        Channel channel = RabbitMQUtils.getChannel();
        channel.queueDeclare(ASYNC_QUEUE_NAME, false, false, false, null);
        channel.confirmSelect(); // 开启 confirm 模式

        /** 异步确认中，处理未确认的消息
         *   1.使用一个适用于高并发情况下的，线程安全有序的哈希表来记录所有发送的消息序号与消息内容，
         *     ConcurrentSkipListMap 可在消息队列和异步确认两个线程间传递信息
         *   2.在消息监听器的确认回调函数中，删除掉已经确认的消息
         *   3.在异步确认完成后，该哈希表中剩余的，即为未确认的消息
         */
        ConcurrentSkipListMap<Long, String> outstandingConfirms = new ConcurrentSkipListMap<>();

        //消息监听器的消息确认成功回调函数，deliveryTag 代表消息编号，multiple 代表是否为批量
        ConfirmCallback ackCallback = (deliveryTag, multiple) -> {
            if (multiple) { //批量
                ConcurrentNavigableMap<Long, String> confirmed =
                        outstandingConfirms.headMap(deliveryTag);
                confirmed.clear();
            } else { //非批量
                outstandingConfirms.remove(deliveryTag);
            }
            System.out.println("确认消息的编号：" + deliveryTag);
        };
        //消息监听器的消息确认失败回调函数，deliveryTag 代表消息编号，multiple 代表是否为批量
        ConfirmCallback nackCallback = (deliveryTag, multiple) -> {
            String message = outstandingConfirms.get(deliveryTag);
            System.out.println("未确认消息的编号：" + deliveryTag + "，未确认消息的内容：" + message);
        };
        //消息监听器，监听消息发送成功/失败
        channel.addConfirmListener(ackCallback, nackCallback);


        long begin = System.currentTimeMillis(); //开始时间
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", ASYNC_QUEUE_NAME, null, message.getBytes());
            //记录所有发送的消息
            outstandingConfirms.put(channel.getNextPublishSeqNo(), message);
        }
        long end = System.currentTimeMillis(); //结束时间

        System.out.println("发布" + MESSAGE_COUNT + "个异步确认消息，耗时：" + (end - begin) + "ms");
    }
}
