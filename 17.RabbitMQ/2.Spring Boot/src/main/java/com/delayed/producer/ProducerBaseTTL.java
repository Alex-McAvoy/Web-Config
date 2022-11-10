package com.delayed.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 基于TTL的延迟队列的生产者
 * @Author: Alex McAvoy
 * @Date: 2022/10/10 11:50
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/ttl")
public class ProducerBaseTTL {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @Description: 使用固定时长的 TTL，每增加一个新的时间需求就要新增一个队列
     * @Param: [message]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/10 13:53
     **/
    @GetMapping("/sendMessage/{message}")
    public void sendMessage(@PathVariable String message) {
        System.out.println("发送一条信息给两个TTL队列A、B:" + message);
        rabbitTemplate.convertAndSend("X", "XA", "消息来自TTL为10S的队列: " + message);
        rabbitTemplate.convertAndSend("X", "XB", "消息来自TTL为40S的队列: " + message);
    }
    
    /**
     * @Description: 使用可变时长的 TTL，每增加一个新的时间需求，传递相应TTL时间参数即可
     * @Param: [message, ttlTime]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/10 13:54
     **/
    @GetMapping("/sendMessage/{message}/{ttlTime}")
    public void sendMessage(@PathVariable String message,@PathVariable String ttlTime) {
        System.out.println("发送一条时长为" + ttlTime + "ms的信息给队列C:" + message);
        rabbitTemplate.convertAndSend("X", "XC", message, correlationData ->{
            correlationData.getMessageProperties().setExpiration(ttlTime);
            return correlationData;
        });
    }
}
