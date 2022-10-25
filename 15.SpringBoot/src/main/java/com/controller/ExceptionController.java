package com.controller;

import com.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 异常处理器
 * @Author: Alex McAvoy
 * @Date: 2022/9/22 17:15
 * @Version: 1.0
 **/
@ControllerAdvice
public class ExceptionController {
    //浏览器客户端返回的都是json，无自适应效果
//    @ResponseBody
//    @ExceptionHandler(UserNotExistException.class)
//    public Map<String,Object> handleException(Exception e){
//        Map<String,Object> map = new HashMap<>();
//        map.put("code","user.notexist");
//        map.put("message",e.getMessage());
//        return map;
//    }

    /**
     * @Description: 用户不存在异常
     * @Param: [e, request]
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/9/22 17:49
     **/
    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        Integer statusCode = 500;//传入自己的错误状态码 4xx 5xx

        request.setAttribute("javax.servlet.error.status_code", statusCode);
        map.put("code", "user.notexist");
        map.put("message", "用户不存在");

        request.setAttribute("ext", map);
        //转发到/error
        return "forward:/error";
    }
}
