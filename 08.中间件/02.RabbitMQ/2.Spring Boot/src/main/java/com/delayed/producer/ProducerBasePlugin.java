package com.delayed.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 基于 rabbitmq_delayed_message_exchange 插件的延迟队列的生产者
 * @author Alex McAvoy
 * @date 2022/10/10 13:58
 * @version 1.0
 **/

@RestController
@RequestMapping("/plugin")
public class ProducerBasePlugin {
    public static final String DELAYED_EXCHANGE = "delayed.exchange";
    public static final String DELAYED_ROUTING_KEY = "delayed.routingKey";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMessage/{message}/{ttlTime}")
    public void sendMsg(@PathVariable String message, @PathVariable Integer ttlTime) {
        System.out.println("发送一条时长为" + ttlTime + "ms的信息给队列:" + message);
        rabbitTemplate.convertAndSend(DELAYED_EXCHANGE, DELAYED_ROUTING_KEY, message,
                correlationData -> {
                    correlationData.getMessageProperties().setDelay(ttlTime);
                    return correlationData;
                });
    }
}
