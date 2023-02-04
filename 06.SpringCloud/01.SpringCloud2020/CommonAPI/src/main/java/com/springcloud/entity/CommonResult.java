package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 传给前端的消息体
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2023/1/31 11:28
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code,String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }
}
