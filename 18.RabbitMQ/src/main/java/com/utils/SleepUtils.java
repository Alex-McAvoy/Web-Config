package com.utils;

/**
 * @Description: 线程沉睡工具类
 * @Author: Alex McAvoy
 * @Date: 2022/10/8 17:36
 * @Version: 1.0
 **/
public class SleepUtils {
    public static void sleep(int second) {
        try {
            Thread.sleep(1000*second);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }
}
