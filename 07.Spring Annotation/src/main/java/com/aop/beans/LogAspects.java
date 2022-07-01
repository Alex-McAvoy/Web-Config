package com.aop.beans;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * @Description: 日志切面类
 * @Author: Alex McAvoy
 * @Date: 2022/7/1 21:58
 * @Version: 1.0
 **/
@Aspect
public class LogAspects {

    //抽取公共切入点表达式
    @Pointcut("execution(public int com.aop.beans.MathCalculator.div(int,int))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        Object args[] = joinPoint.getArgs(); //方法参数列表
        String name = joinPoint.getSignature().getName(); //方法名
        System.out.println("方法 " + name + " 准备运行，参数列表：" + Arrays.asList(args));
    }

    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName(); //方法名
        System.out.println("方法 " + name + " 结束");
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(Object result) {
        System.out.println("除法正常返回，运行结果：" + result);
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(Exception exception) {
        System.out.println("除法异常，异常信息：" + exception);
    }
}
