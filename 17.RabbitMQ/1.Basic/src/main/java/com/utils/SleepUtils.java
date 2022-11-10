package com.utils;

/**
 * 线程沉睡工具类
 * @author Alex McAvoy
 * @date 2022/10/8 17:36
 * @version 1.0
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
