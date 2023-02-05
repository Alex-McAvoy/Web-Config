package com.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.constant.CommonResult;
import com.springcloud.handler.SentinelBlockHandler;
import org.springframework.web.bind.annotation.*;


/**
 * Sentinel流量治理控制器
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2023/1/31 11:49
 **/
@RestController
public class FlowLimitController {

    /**
     * 按资源名称限流
     *
     * @return com.springcloud.constant.CommonResult
     * @author Alex McAvoy
     * @date 2023/2/5 13:09
     **/
    @GetMapping("/byResource")
    @SentinelResource(value = "resource", blockHandler = "handleResourceException")
    public CommonResult byResource() {
        return new CommonResult(200, "按资源名称限流");
    }

    /**
     * byResource方法流量控制处理类
     *
     * @param e BlockException
     * @return com.springcloud.constant.CommonResult
     * @author Alex McAvoy
     * @date 2023/2/5 13:04
     **/
    public CommonResult handleResourceException(BlockException e) {
        return new CommonResult(400, "服务不可用", e.getClass().getCanonicalName());
    }

    /**
     * 按URL限流，违反Sentinel限流规则会返回自带的处理规则
     *
     * @return com.springcloud.constant.CommonResult
     * @author Alex McAvoy
     * @date 2023/2/5 13:12
     **/
    @GetMapping("/byUrl")
    @SentinelResource(value = "url")
    public CommonResult byUrl() {
        return new CommonResult(200, "按URL限流");
    }

    /**
     * 全局自定义限流处理
     *
     * @return com.springcloud.constant.CommonResult
     * @author Alex McAvoy
     * @date 2023/2/5 16:09
     **/
    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = SentinelBlockHandler.class,
            blockHandler = "handlerException")
    public CommonResult customerBlockHandler() {
        return new CommonResult(200, "全局自定义限流处理");
    }

    /**
     *  fallback与blockHandler
     * 对于@SentinelResource，有：
     *  - @SentinelResource(value = "exception", fallback = "handlerFallback")
     *    fallback仅处理业务异常
     *  - @SentinelResource(value = "exception", blockHandler = "handlerBlock")
     *    blockHandler处理Sentinel控制台违规
     *  - @SentinelResource(value = "exception",
     *             fallback = "handlerFallback",
     *             blockHandler = "handlerBlock")
     *    当两者都配置时，被限流降级而抛出的BlockException只会进入blockHandler
     * @param id  id
     * @return com.springcloud.constant.CommonResult
     * @author Alex McAvoy
     * @date 2023/2/5 18:03
     **/
    @GetMapping("/exception/{id}")
    @SentinelResource(value = "exception",
            fallback = "handlerFallback",
            blockHandler = "handlerBlock")
    public CommonResult exception(@PathVariable Integer id) {
        if (id <= 0) {
            throw new IllegalArgumentException("非法参数异常");
        } else if (id >= 10) {
            throw new NullPointerException("id无对应记录，空指针异常");
        }
        return new CommonResult(200, "业务成功", id);
    }

    public CommonResult handlerFallback(@PathVariable Integer id, Throwable e) {
        return new CommonResult(400, "业务异常", e.getMessage());
    }

    public CommonResult handlerBlock(BlockException e) {
        return new CommonResult(400, "服务不可用", e.getClass().getCanonicalName());
    }
}
