package com.handlers;

import com.entities.Address;
import com.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * TODO
 * @author Alex McAvoy
 * @date 2022/3/7 15:36
 * @version 1.0
 **/

@SessionAttributes(value = {"user"}, types = {String.class})
@Controller
@RequestMapping("/test4")
public class TestModelAndView {
    private static final String SUCCESS = "modelAndViewSuccess";

    @RequestMapping("toTestModelAndView")
    public String toTestModelAndView() {
        System.out.println("To Test ModelAndView");
        return "testModelAndView";
    }

    /**
     * 返回值为 ModelAndView 类型，其中可以包含视图和模型信息
     * SpringMVC 会将 model 中的数据放到 Request 域对象中
     * @return org.springframework.web.servlet.ModelAndView
     * @author Alex McAvoy
     * @date 2022/3/7 15:47
     **/
    @RequestMapping("testModelAndView")
    public ModelAndView testModelAndView() {
        System.out.println("test ModelAndView");

        String viewName = SUCCESS;
        ModelAndView modelAndView = new ModelAndView(viewName);
        //添加模型数据到 ModelAndView 中
        modelAndView.addObject("time", new Date());

        return modelAndView;
    }

    /**
     * Map 类型参数的数据会放到 Request 域对象中
     * @param map model
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/7 15:57
     **/
    @RequestMapping("testMap")
    public String testMap(Map<String, Object> map) {
        System.out.println("Test Map");
        map.put("names", Arrays.asList("Tom", "Mike", "Alex"));
        return SUCCESS;
    }

    /**
     * @SessionAttributes() 放与类声明之前，可以将属性放到 session 中（使用 value 属性）
     * 其也可以通过指定 type，来将模型属性放于 session 中
     * @param map model
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/7 16:06
     **/
    @RequestMapping("testSessionAttributes")
    public String testSessionAttributes(Map<String, Object> map) {
        Address address = new Address("AA", "aa");
        User user = new User("Tom", "123", "123", 23, address);
        map.put("user", user);
		//通过 @SessionAttributes() 中的 type 来指定存放 String 类型
        map.put("school", "123"); 
        return SUCCESS;
    }


    /**
     * 模拟修改操作
     *               运行流程：
     *               1. 执行 @ModelAttribute 注解修饰的方法：从数据库中取出对象，将对象放入 Map 中，键为 user
     *               2. SpringMVC 从 Map 中取出 User 对象，并将表单的请求参数赋值给该 User 对象的对应属性
     *               3. SpringMVC 将上述对象传入目标方法的参数
     *               注意：在 @ModelAttribute 注解修饰的方法中，放入到 Map 时的键需要与修改方法的入参一致
     * @param user 用户
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/7 16:22
     **/
    @RequestMapping("testModelAttribute")
    public String testModelAttribute(@ModelAttribute("user") User user) {
        System.out.println("修改对象, User: " + user);
        return SUCCESS;
    }

    /**
     * 由 @ModelAttribute 标记的方法，会在每个目标方法执行前被 SpringMVC 调用
     *               @ModelAttrribute 可修饰目标方法 POJO 类型的入参，其 value 属性具有以下作用：
     *                1. SpringMVC 会用 value 属性值在 implicitModel 中查找对应对象，若存在则会直接传入到目标方法中
     *                2. SpringMCV 会以 value 为 key，POJO 类型的对象为 value，存入 request 中
     * @param name 用户名
     * @param map model
     * @return void
     * @author Alex McAvoy
     * @date 2022/3/7 16:28
     **/
    @ModelAttribute
    public void getUser(@RequestParam(value = "name",required = false) String name,Map<String,Object> map) {
        if(name != null) {
            //模拟根据 name 从数据库获取对象
            Address address = new Address("AA","aa");
            User user = new User(name,"123456","123@qq.com",25,address);
            System.out.println("获取对象: " + user);

            map.put("user",user);
        }
    }
}
