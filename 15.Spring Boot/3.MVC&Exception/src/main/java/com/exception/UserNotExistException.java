package com.exception;

/**
 * @Description: 用户不存在异常
 * @Author: Alex McAvoy
 * @Date: 2022/9/21 14:13
 * @Version: 1.0
 **/
public class UserNotExistException extends RuntimeException{
    public UserNotExistException() {
        super("用户不存在");
    }
}
