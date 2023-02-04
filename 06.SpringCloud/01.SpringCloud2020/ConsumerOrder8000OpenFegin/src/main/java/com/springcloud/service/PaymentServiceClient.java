package com.springcloud.service;

import com.springcloud.constant.CommonResult;
import com.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 消费者所调用的OpenFegin接口，对应生产者所提供的PaymentService接口
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2023/2/1 10:35
 **/
@Component
@FeignClient("payment-service-eureka")
public interface PaymentServiceClient {
    @GetMapping("/provider/payment/get/{id}")
    public CommonResult get(@PathVariable("id") Integer id);

    @PostMapping("/provider/payment/add")
    public CommonResult add(@RequestBody Payment payment);

    @GetMapping("/provider/payment/discovery")
    public Object discovery();
}
