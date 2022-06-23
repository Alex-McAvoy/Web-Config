package com.actions;

import com.entities.Employee;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.service.DepartmentService;
import com.service.EmployeeService;
import org.apache.struts2.interceptor.RequestAware;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

/**
 * @Description: Employee 类的 Action
 * @Author: Alex McAvoy
 * @Date: 2022/2/28 0:04
 * @Version: 1.0
 **/
public class EmployeeAction extends ActionSupport implements RequestAware, ModelDriven<Employee>, Preparable {
    private static final long serialVersionUID = 1L;

    /**
     * 设置 request
     **/
    private Map<String, Object> request;

    @Override
    public void setRequest(Map<String, Object> arg0) {
        this.request = arg0;
    }

    /**
     * 显示信息
     **/
    private EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public String list() {
        request.put("employees", employeeService.getAll());
        return "list";
    }

    /**
     * Struts2使用ajax
     **/
    private InputStream inputStream;

    public InputStream getInputStream() { // Struts2 使用 Ajax 删除时使用
        return inputStream;
    }

    /**
     * 删除信息 ajax
     **/
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public String delete() {
        try {
            employeeService.delete(id);
            inputStream = new ByteArrayInputStream("1".getBytes("UTF-8")); //Ajax删除使用
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            try {
                inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        }
        return "ajax-success";
    }

    /**
     * 显示录入界面
     **/
    private DepartmentService departmentService;

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public String input() {
        request.put("departments", departmentService.getAll());
        return INPUT;
    }

    /**
     * 员工录入
     **/
    private Employee model;

    @Override
    public Employee getModel() {
        return model;
    }

    @Override
    public void prepare() throws Exception {
    }

    public String save() {
        if (id == null) {
            model.setCreateTime(new Date());
        }
        employeeService.saveOrUpdate(model);
        return SUCCESS;
    }

    //根据id来判断 save 方法准备的 model 是 new 的还是从数据库获取的
    public void prepareSave() {
        if (id == null) {
            model = new Employee();
        } else {
            model = employeeService.get(id);
        }
    }

    /**
     * 编辑界面回显
     **/
    public void prepareInput() {
        if (id != null) {
            model = employeeService.get(id);
        }
    }
}
