package com.springcloud.service.Impl;

import com.springcloud.dao.PaymentDao;
import com.springcloud.entity.Payment;
import com.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 生产者-支付模块Service的实现类
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2023/1/31 11:46
 **/
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;

    @Override
    public Payment getPaymentById(Integer id) {
        return paymentDao.selectById(id);
    }

    @Override
    public int addPayment(Payment payment) {
        return paymentDao.addPayment(payment);
    }
}
