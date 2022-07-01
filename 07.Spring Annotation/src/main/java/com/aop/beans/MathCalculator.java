package com.aop.beans;

/**
 * @Description: 目标业务逻辑类
 * @Author: Alex McAvoy
 * @Date: 2022/7/1 21:57
 * @Version: 1.0
 **/
public class MathCalculator {

    public int div(int i,int j){
        System.out.println("除法开始运行");
        return i/j;
    }
}
