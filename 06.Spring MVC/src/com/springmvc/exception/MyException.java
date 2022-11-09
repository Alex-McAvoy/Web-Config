package com.springmvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/3/8 22:30
 * @Version: 1.0
 **/
//该注解放在类上，当抛出该异常时，会弹出相应状态码和错误原因
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "错误")
public class MyException extends RuntimeException{

}
