package com.spingcloud.service.Impl;

import com.spingcloud.service.MessageProviderService;
import com.springcloud.constant.MessageConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * 消息生产者接口实现类
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2023/2/2 19:47
 **/
@Service
@Slf4j
public class MessageProviderServiceImpl implements MessageProviderService {

    @Autowired
    private StreamBridge streamBridge;

    @Override
    public void sendMessage(String message) {
        Message<String> build = MessageBuilder.withPayload(message).build();
        streamBridge.send(MessageConstant.MESSAGE1_OUTPUT, build);
        log.info("消息发送成功");
    }
}
