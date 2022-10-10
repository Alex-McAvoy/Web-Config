package com.publishack.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @Description: 高级发布确认的配置类
 * @Author: Alex McAvoy
 * @Date: 2022/10/10 19:14
 * @Version: 1.0
 **/
@Configuration
public class ConfirmConfig {

    /** 高级发布确认
     *   - 当使用发布确认时，一般采用手动应答并处理
     *   - 当 RabbitMQ 服务因为某些原因宕机时，已发送还未来得及确认的消息会丢失
     *   - 此时可在原有发布确认的基础上增加缓存机制，以防止消息丢失，即高级发布确认
     * **/

    public static final String CONFIRM_EXCHANGE = "confirm_exchange";
    public static final String CONFIRM_QUEUE = "confirm_queue";
    public static final String CONFIRM_ROUTING_KEY = "confirm.routingKey";

    @Bean("confirmExchange")
    public DirectExchange confirmExchange() {
        //将确认交换机上的内容同时转发到备份交换机上
        return ExchangeBuilder.directExchange(CONFIRM_EXCHANGE).
                withArgument("alternate-exchange",BACKUP_EXCHANGE).build();
    }

    @Bean("confirmQueue")
    public Queue confirmQueue() {
        return new Queue(CONFIRM_QUEUE);
    }

    @Bean
    public Binding queueBindingExchange(@Qualifier("confirmQueue") Queue confirmQueue,
                                        @Qualifier("confirmExchange") DirectExchange confirmExchange) {
        return BindingBuilder.bind(confirmQueue).to(confirmExchange).with(CONFIRM_ROUTING_KEY);
    }

    /** 备份交换机
     *   - 对于高级发布确认，当开启确认回调函数与发布回退后，可设置一备份交换机
     *   - 对于无法投递的消息，将发布给备份交换机
     *   - 备份交换机一般绑定两个队列，一个用来投递发送失败的消息，一个用来报警
     *   - 当启用备份交换机后，若有消息回退，将会回退到备份交换机上，即备份交换机优先级高
     * **/
    public static final String BACKUP_EXCHANGE = "backup_exchange";
    public static final String BACKUP_QUEUE = "backup_queue";
    public static final String WARNING_QUEUE = "warning_queue";

    @Bean("backupExchange")
    public FanoutExchange backupExchange() {
        return new FanoutExchange(BACKUP_EXCHANGE);
    }

    @Bean("backupQueue")
    public Queue backupQueue() {
        return new Queue(BACKUP_QUEUE);
    }

    @Bean("warningQueue")
    public Queue warningQueue() {
        return new Queue(WARNING_QUEUE);
    }

    @Bean
    public Binding backupQueueBindBackupExchange(@Qualifier("backupQueue") Queue backupQueue,
                                                 @Qualifier("backupExchange") FanoutExchange backupExchange) {
        return BindingBuilder.bind(backupQueue).to(backupExchange);
    }

    @Bean
    public Binding warningQueueBindBackupExchange(@Qualifier("warningQueue") Queue warningQueue,
                                                  @Qualifier("backupExchange") FanoutExchange backupExchange) {
        return BindingBuilder.bind(warningQueue).to(backupExchange);
    }
}
