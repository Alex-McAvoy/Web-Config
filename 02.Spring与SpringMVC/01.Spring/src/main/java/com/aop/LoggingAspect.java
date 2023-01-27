package com.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


/**
 * 将该类声明为一个切面: 需要将该类放到 IOC 容器中(@Component),再声明为一个切面(@Aspect)
 *               可以使用 @Order(x) 来指定切面优先级,值越小优先级越大
 * @author Alex McAvoy
 * @date 2022/2/25 2:13
 * @version 1.0
 **/
@Component
@Aspect
public class LoggingAspect {

    /**
     * 前置通知: 方法执行前执行
     * @param joinPoint JoinPoint
     * @author Alex McAvoy
     * @date 2022/2/25 3:37
     **/
    @Before("execution(public int com.aop.Calculator.*(int ,int ))")
    public void beforeMethod(JoinPoint joinPoint) {
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        //获取方法参数值
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("The method " + methodName + " before with " + args);
    }

    /**
     * 后置通知: 方法执行后执行,无论该方法是否出现异常
     * @param joinPoint JoinPoint
     * @author Alex McAvoy
     * @date 2022/2/25 3:37
     **/
    @After("execution(* com.aop.Calculator.*(int ,int ))")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " ends");
    }

    /**
     * 返回通知: 方法正常结束后执行,可访问到方法的返回值
     * @param joinPoint JoinPoint
	 * @param result Object
     * @author Alex McAvoy
     * @date 2022/2/25 3:37
     **/
    @AfterReturning(value = "execution(* com.aop.Calculator.*(..))",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " ends with " + result);
    }

    /**
     * 异常通知: 目标方法出现异常时执行,可访问到异常对象,
     * 且可以指定出现特定异常时执行通知代码
     * @param joinPoint JoinPoint
	 * @param exception Exception
     * @author Alex McAvoy
     * @date 2022/2/25 3:37
     **/
    @AfterThrowing(value = "execution(* com.aop.Calculator.*(..))",
            throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " occurs exception " + exception);
    }

    /**
     * 利用 @Pointcut 可声明切入点表达式,此时各种通知无需填入 execution,利用该函数名代替即可
     * @author Alex McAvoy
     * @date 2022/2/25 3:52
     **/
    @Pointcut("execution(* com.aop.Calculator.*(..))")
    public void declareJoinPointExpression() {}

    /**
     * 环绕通知: 类似动态代理全过程,需要携带 ProceedingJoinPoint 参数
     * @param pjp ProceedingJoinPoint可决定是否执行目标方法
     * @return Object 必须有返回值, 返回值即目标方法的返回值
     * @author Alex McAvoy
     * @date 2022/2/25 3:38
     **/
    @Around("declareJoinPointExpression()")
    public Object aroundMethod(ProceedingJoinPoint pjp) {
        Object res = null;
        String methodName = pjp.getSignature().getName();
        List<Object> args = Arrays.asList(pjp.getArgs());
        try {
            //前置通知
            System.out.println("-->The method " + methodName + " before with " + args);
            //执行目标方法
            res = pjp.proceed();
            //返回通知
            System.out.println("-->The method " + methodName + " ends with " + res);
        } catch (Throwable e) {
            //异常通知
            System.out.println("-->The method " + methodName + " occurs exception " + e);
        }
        //后置通知
        System.out.println("-->The method " + methodName + " ends");

        return res;
    }
}

