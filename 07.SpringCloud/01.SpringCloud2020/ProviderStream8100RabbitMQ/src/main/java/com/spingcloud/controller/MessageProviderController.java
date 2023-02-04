package com.spingcloud.controller;

import com.spingcloud.service.MessageProviderService;
import com.springcloud.constant.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息生产者Controller
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2023/2/2 20:03
 **/
@RestController
public class MessageProviderController {
    @Autowired
    private MessageProviderService messageProviderService;

    @GetMapping("/sendMessage/{message}")
    public CommonResult sendMessage(@PathVariable("message") String message) {
        try {
            messageProviderService.sendMessage(message);
            return new CommonResult(200, "消息发送成功", message);
        } catch (Exception e) {
            return new CommonResult(400, "消息发送失败", e);
        }

    }
}
