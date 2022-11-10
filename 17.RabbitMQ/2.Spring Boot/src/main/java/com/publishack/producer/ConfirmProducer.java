package com.publishack.producer;

import com.publishack.config.ConfirmConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 高级发布确认生产者
 * @author Alex McAvoy
 * @date 2022/10/10 19:22
 * @version 1.0
 **/
@RestController
@RequestMapping("/confirm")
public class ConfirmProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMessage/{message}")
    public void sendMessage(@PathVariable String message) {
        //正确
        CorrelationData correlationData1 = new CorrelationData("1");
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE,ConfirmConfig.CONFIRM_ROUTING_KEY,message,correlationData1);
        System.out.println("发送消息:" + message + " key1");

        //交换机错误
        CorrelationData correlationData2 = new CorrelationData("2");
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE +"123",ConfirmConfig.CONFIRM_ROUTING_KEY,message,correlationData2);
        System.out.println("发送消息:" + message+ " key2");

        //Routing Key错误
        CorrelationData correlationData3 = new CorrelationData("3");
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE,ConfirmConfig.CONFIRM_ROUTING_KEY + "123",message,correlationData3);
        System.out.println("发送消息:" + message+ " key3");
    }

}
