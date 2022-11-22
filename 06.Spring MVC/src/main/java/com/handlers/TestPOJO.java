package com.handlers;

import com.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SpringMVC 会按请求参数名和 POJO 属性名自动匹配，为对象填充属性值，且支持级联属性
 * @author Alex McAvoy
 * @date 2022/3/6 20:07
 * @version 1.0
 **/
@Controller
@RequestMapping("/test3")
public class TestPOJO {
    private static final String SUCCESS = "success";

    @RequestMapping("/toTestPOJO")
    public String toTestPOJO() {
        System.out.println("To Test POJO");
        return "testPOJO";
    }

    @RequestMapping("testPOJO")
    public String testPOJO(User user) {
        System.out.println("Test POJO: " + user);
        return SUCCESS;
    }
}
