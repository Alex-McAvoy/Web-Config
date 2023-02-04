package com.controller;

import com.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理器
 * @author Alex McAvoy
 * @date 2022/9/22 17:15
 * @version 1.0
 **/
@ControllerAdvice
public class ExceptionController {

    /** 浏览器客户端返回的是json，无自适应效果
     * @param e 异常
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author Alex McAvoy
     * @date 2022/9/22 17:15
     **/
//    @ResponseBody
//    @ExceptionHandler(UserNotExistException.class)
//    public Map<String,Object> handleException(Exception e){
//        Map<String,Object> map = new HashMap<>();
//        map.put("code","user.notExist");
//        map.put("message",e.getMessage());
//        return map;
//    }

    /** 用户不存在异常
     * @param e 异常
     * @param request HttpServletRequest
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/9/22 17:49
     **/
    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(5);
        //传入自定义的错误状态码 4xx 5xx
        Integer statusCode = 500;

        request.setAttribute("javax.servlet.error.status_code", statusCode);
        map.put("code", "user.notExist");
        map.put("message", "用户不存在");

        request.setAttribute("ext", map);
        //转发到/error
        return "forward:/error";
    }
}
