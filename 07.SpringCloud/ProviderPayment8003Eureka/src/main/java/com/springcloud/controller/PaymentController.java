package com.springcloud.controller;

import com.springcloud.entity.CommonResult;
import com.springcloud.entity.Payment;
import com.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 生产者-支付模块controller
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2023/1/31 11:49
 **/
@RestController
@Slf4j
@RequestMapping("/provider/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/add")
    public CommonResult add(@RequestBody Payment payment) {
        int result = paymentService.addPayment(payment);
        log.info("插入结果:" + result);

        if (result > 0) {
            return new CommonResult(200, "插入成功", result);
        } else {
            return new CommonResult(400, "插入失败");
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult get(@PathVariable("id") Integer id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果:" + payment);

        if (payment != null) {
            return new CommonResult(200, "查询成功", payment);
        } else {
            return new CommonResult(400, "查询失败，查询id:" + id);
        }
    }

    /**
     * 服务发现
     *
     * @return java.lang.Object
     * @author Alex McAvoy
     * @date 2023/1/31 22:04
     **/
    @GetMapping("/discovery")
    public Object discovery() {
        //获取名为payment-service的微服务
        List<ServiceInstance> srvList = discoveryClient.getInstances("payment-service-eureka");

        for (ServiceInstance element : srvList) {
            log.info("id:" + element.getInstanceId() +
                    ",host:" + element.getHost() +
                    ",port:" + element.getPort() +
                    ",uri:" + element.getUri());
        }

        return this.discoveryClient;
    }
}
