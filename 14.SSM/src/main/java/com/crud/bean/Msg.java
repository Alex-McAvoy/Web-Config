package com.crud.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 通用返回类
 * @Author: Alex McAvoy
 * @Date: 2022/4/20 23:15
 * @Version: 1.0
 **/
public class Msg {
    public int code; //状态码，100-成功，200-失败
    private String msg; //提示信息
    private Map<String, Object> extend = new HashMap<String, Object>(); //用户返回给浏览器的数据

    public static Msg success() {
        Msg result = new Msg();
        result.setCode(100);
        result.setMsg("处理成功！");
        return result;
    }

    public static Msg fail() {
        Msg result = new Msg();
        result.setCode(100);
        result.setMsg("处理失败！");
        return result;
    }

    public Msg add(String key, Object value) {
        this.getExtend().put(key, value);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
