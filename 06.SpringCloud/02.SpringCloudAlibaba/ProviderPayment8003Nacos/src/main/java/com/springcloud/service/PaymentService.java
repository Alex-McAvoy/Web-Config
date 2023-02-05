package com.springcloud.service;

import com.springcloud.entity.Payment;

/**
 * 生产者-支付模块service接口
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2023/1/31 11:35
 **/
public interface PaymentService {
    /** 通过paymentId获取支付订单
     * @param id  paymentId
     * @return com.springcloud.entity.Payment
     * @author Alex McAvoy
     * @date 2023/1/31 11:44
     **/
    public Payment getPaymentById(Integer id);
    /** 添加一个支付订单
     * @param payment  payment实体
     * @return int
     * @author Alex McAvoy
     * @date 2023/1/31 11:45
     **/
    public int addPayment(Payment payment);
}
