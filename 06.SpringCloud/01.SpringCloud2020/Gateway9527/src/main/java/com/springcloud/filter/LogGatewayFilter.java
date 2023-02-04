package com.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Date;

/**
 * Gateway全局日志过滤器
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2023/2/1 16:44
 **/
@Component
@Slf4j
public class LogGatewayFilter implements GlobalFilter, Ordered {
    /** Gateway全局自定义过滤器
     * @param exchange 网络交换机
     * @param chain 过滤器链
     * @return reactor.core.publisher.Mono<java.lang.Void>
     * @author Alex McAvoy
     * @date 2023/2/1 17:00
     **/
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("进入Gateway全局过滤器：" + new Date());
        URI uri = exchange.getRequest().getURI();
        log.info("获取到请求路径：" + uri.toString());
        return chain.filter(exchange);
    }

    /** Ordered 接口实现方法
     *  用于控制Filter过滤器顺序，值越低当前过滤器优先级越高
     * @return int
     * @author Alex McAvoy
     * @date 2023/2/1 17:01
     **/
    @Override
    public int getOrder() {
        return 0;
    }
}
