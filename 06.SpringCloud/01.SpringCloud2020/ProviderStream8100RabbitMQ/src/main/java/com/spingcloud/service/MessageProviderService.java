package com.spingcloud.service;

/**
 * 消息生产者接口
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2023/2/2 19:45
 **/
public interface MessageProviderService {
    /** 消息发送
     * @param message 消息
     * @author Alex McAvoy
     * @date 2023/2/2 21:43
     **/
    public void sendMessage(String message);
}
