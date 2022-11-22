package com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 注解@ResponseStatus放在类上，当抛出该异常时，会弹出相应状态码和错误原因
 * @author Alex McAvoy
 * @date 2022/3/8 22:30
 * @version 1.0
 **/
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "错误")
public class MyException extends RuntimeException{

}
