package com.handlers;

import com.exception.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 若在当前 Handler 中找不到 @ExceptionHandler，则会去 @ControllerAdvice 注解的类中寻找
 * @author Alex McAvoy
 * @date 2022/3/8 22:11
 * @version 1.0
 **/
@ControllerAdvice
@RequestMapping("test5")
public class TestException {
    private static final String SUCCESS = "success";

    @RequestMapping("toTestException")
    public String toTestException() {
        System.out.println("To Test Exception");
        return "testException";
    }

    /**
     * @ExceptionHandler
     * @param i int
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/8 22:43
     **/
    @RequestMapping("testException")
    public String testException(@RequestParam("i") int i) {
        System.out.println("Test Exception, result: " + (10 / i));
        return SUCCESS;
    }

    /**
     * 对发生异常的对象进行处理
     * @param e 异常
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/8 22:21
     **/
    @ExceptionHandler({ArithmeticException.class})
    public ModelAndView handleArithmeticException(Exception e) {
        System.out.println("Exception: " + e);
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception", e);
        return mv;
    }

    /**
     * @ResponseStatus
     * @param i int
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/8 22:42
     **/
    @RequestMapping("testResponseStatus")
    public String testResponseStatus(@RequestParam("i") int i) {
        if (i == 10) {
            throw new MyException();
        }

        System.out.println("Test Exception, result: " + (10 / i));
        return SUCCESS;
    }

    /**
     * 通过注解配置，对所有异常统一处理，且其可以将异常信息存入 Request 中
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/8 22:45
     **/
    @RequestMapping("testSimpleMappingExceptionResolver")
    public String testSimpleMappingExceptionResolver(@RequestParam("i") int i) {
        String [] val = new String[10];
        System.out.println(val[i]);
        return SUCCESS;
    }
}
