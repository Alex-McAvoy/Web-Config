package com.springcloud.controller;

import com.springcloud.entity.CommonResult;
import com.springcloud.entity.Payment;
import com.springcloud.service.PaymentServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @Autowired
    private PaymentServiceClient paymentServiceClient;

    @RequestMapping("/add")
    public CommonResult add(Payment payment) {
        return paymentServiceClient.add(payment);
    }

    @RequestMapping("/get/{id}")
    public CommonResult get(@PathVariable("id") Integer id) {
        return paymentServiceClient.get(id);
    }

    @RequestMapping("/discovery")
    public Object discovery() {
        return paymentServiceClient.discovery();
    }
}
