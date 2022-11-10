package com.publishack.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Description: 交换机确认回调接口实现类
 * @Author: Alex McAvoy
 * @Date: 2022/10/10 19:34
 * @Version: 1.0
 **/
@Component
public class ConfirmCallBackConfig implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnsCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() { //注入
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    /** 确认回调方法
     *   - 交换机接收成功，回调
     *    1.correlationData：保存回调消息的 ID 与相关信息
     *    2.ack：交换机收到消息 ack=true
     *    3.cause：造成交换机接收失败原因，cause=null
     *   - 交换机接收失败，回调
     *    1.correlationData：保存回调消息的 ID 与相关信息
     *    2.ack：交换机收到消息 ack=false
     *    3.cause：造成交换机接收失败原因，cause=失败的原因
     *
     * **/
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = correlationData != null ? correlationData.getId():"";
        if(ack) {
            System.out.println("交换机收到消息，id 为：" + id);
        } else {
            System.out.println("交换机接收消息 id 为 " + id + " 的消息失败，由于：" +cause);
        }
    }

    /** 发布回退方法
     *  - 在开启交换机的确认回调函数 CallBack 后，交换机接收到消息会给生产者发确认消息，
     *    发现消息不可路由时，消息会被丢弃，此时，生产者是不知道消息被丢弃的
     *  - 可以通过设置 mandatory 参数，使消息在传递过程中，不可达时将消息返回给生产者
     * **/
    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        String msg = new String(returnedMessage.getMessage().getBody());
        System.out.println("消息：" + msg + "被交换机" +
                returnedMessage.getExchange()+ "退回" +
                "，Routing Key：" + returnedMessage.getRoutingKey() +
                "，错误代码：" + returnedMessage.getReplyCode() +
                "，Cause By：" + returnedMessage.getReplyText());
    }

}
