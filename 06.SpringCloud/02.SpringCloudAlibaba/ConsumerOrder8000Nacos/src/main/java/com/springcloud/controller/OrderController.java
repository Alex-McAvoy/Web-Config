package com.springcloud.controller;

import com.springcloud.constant.CommonResult;
import com.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * 消费者-订单模块Controller
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2023/1/31 16:05
 **/
@RestController
@Slf4j
@RequestMapping("/consumer/payment")
public class OrderController {
    @Value("${service-url.provider-service}")
    private String serverUrl;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/add")
    public CommonResult add(Payment payment) {
        return restTemplate.postForObject(serverUrl + "/add", payment, CommonResult.class);
    }

    @RequestMapping("/get/{id}")
    public CommonResult get(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(serverUrl + "/get/" + id, CommonResult.class);
    }

    @RequestMapping("/discovery")
    public Object discovery() {
        return restTemplate.getForObject(serverUrl + "/discovery", Object.class);
    }
}
