package com.springcloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 生产者-支付模块dao接口
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2023/1/31 11:31
 **/
@Mapper
public interface PaymentDao extends BaseMapper<Payment> {
    /** 添加一个新支付订单
     * @param payment  支付模块实体
     * @return int
     * @author Alex McAvoy
     * @date 2023/1/31 11:36
     **/
    public int addPayment(Payment payment);
}
