package com.actions;

import com.service.EmployeeService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * TODO
 * @author Alex McAvoy
 * @date 2022/2/28 5:16
 * @version 1.0
 **/
public class ValidateNameAction {
    /**
     * Struts2使用ajax
     **/
    private InputStream inputStream;

    public InputStream getInputStream() { // Struts2 使用 Ajax 删除时使用
        return inputStream;
    }

    /**
     * 录入界面用户名验证
     **/
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    private EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public String validateName() {
        if (employeeService.nameIsValid(name)) {
            try {
                inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            try {
                inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return "ajax-success1";
    }
}
