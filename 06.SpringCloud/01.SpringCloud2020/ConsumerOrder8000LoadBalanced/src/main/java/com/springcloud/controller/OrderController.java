package com.springcloud.controller;

import com.springcloud.constant.CommonResult;
import com.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final String PAYMENT_URL = "http://payment-service-eureka/provider/payment";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/add")
    public CommonResult add(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/add", payment, CommonResult.class);
    }

    @RequestMapping("/get/{id}")
    public CommonResult get(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PAYMENT_URL + "/get/" + id, CommonResult.class);
    }

    @RequestMapping("/discovery")
    public Object discovery() {
        return restTemplate.getForObject(PAYMENT_URL + "/discovery", Object.class);
    }
}
