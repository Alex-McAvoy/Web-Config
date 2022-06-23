package com.aop;

import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/2/25 2:08
 * @Version: 1.0
 **/

@Component("calculatorImpl")
public class CalculatorImpl implements Calculator {

    @Override
    public int add(int i, int j) {
        int res = i + j;
        return res;
    }

    @Override
    public int sub(int i, int j) {
        int res = i - j;
        return res;
    }

    @Override
    public int mul(int i, int j) {
        int res = i * j;
        return res;
    }

    @Override
    public int div(int i, int j) {
        int res = i / j;
        return res;
    }
}
