package com.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.constant.CommonResult;

/**
 * Sentinel流量治理全局自定义限流处理类
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2023/2/5 15:27
 **/
public class SentinelBlockHandler {
    public static CommonResult handlerException(BlockException e) {
        return new CommonResult(400, "服务不可用", e.getClass().getCanonicalName());
    }
}