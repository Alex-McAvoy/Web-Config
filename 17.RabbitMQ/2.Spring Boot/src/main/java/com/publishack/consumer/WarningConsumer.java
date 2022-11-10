package com.publishack.consumer;

import com.publishack.config.ConfirmConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 高级发布报警消费者
 * @author Alex McAvoy
 * @date 2022/10/10 19:26
 * @version 1.0
 **/
@Component
public class WarningConsumer {
    @RabbitListener(queues = ConfirmConfig.WARNING_QUEUE)
    public void receiveWarningMessage(Message message) {
        String msg = new String(message.getBody());
        System.out.println("报警发现不可路由的消息:" + msg);
    }
}
