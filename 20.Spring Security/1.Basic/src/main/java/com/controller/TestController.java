package com.controller;

import com.bean.Person;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试 Spring Security
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2022/10/31 13:44
 **/
@RestController
@RequestMapping("/test")
public class TestController {
        /**
     * 使用 @Secured 注解进入方法前完成角色验证
     * 当具有对应角色时，才可访问方法
     * 无需再 SecurityConfig 配置类中配置
     **/
    @Secured({"ROLE_role1", "ROLE_role2"})
    @GetMapping("/secured")
    @ResponseBody
    public String secured() {
        return "secured";
    }

    /**
     * 使用 @PreAuthorize 注解进入方法前完成权限、角色验证
     * 在其中可以使用 hasAuthority、hasAnyAuthority、hasAnyRole、hasRole 等方法
     * 从而设定进入方法前要验证的权限、角色
     * 无需再 SecurityConfig 配置类中配置
     **/
    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/preAuthorize")
    @ResponseBody
    public String preAuthorize() {
        return "preAuthorize";
    }

    /**
     * 使用 @PostAuthorize 注解进入方法后完成权限、角色验证
     * 在其中可以使用 hasAuthority、hasAnyAuthority、hasAnyRole、hasRole 等方法
     * 从而设定进入方法后要验证的权限、角色
     * 无需再 SecurityConfig 配置类中配置
     * 该注解较为少用，无论有没有相应权限，都会执行方法体，在返回前进行校验
     **/
    @PostAuthorize("hasAuthority('xxx')")
    @GetMapping("/postAuthorize")
    @ResponseBody
    public String postAuthorize() {
        System.out.println("进入方法");
        return "postAuthorize";
    }

    /**
     * 使用 @PostFilter 注解可在返回前对数据进行过滤
     **/
    @PostAuthorize("hasAuthority('admin')")
    @GetMapping("/postFilter")
    @ResponseBody
    @PostFilter("filterObject.username == 'user1'")
    public List<Person> postFilter() {
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person(1, "user1", "123456", "", ""));
        list.add(new Person(1, "user3", "123456", "", ""));
        list.add(new Person(1, "user2", "123456", "", ""));
        return list;
    }

    /**
     * 使用 @PostFilter 注解可在进入方法前对传入数据进行过滤
     **/
    @PostAuthorize("hasAuthority('admin')")
    @GetMapping("/preFilter")
    @ResponseBody
    @PreFilter(value = "filterObject.id%2 == 0")
    public List<Person> preFilter(@RequestBody List<Person> list) {
        list.forEach(t -> {
            System.out.println(t.getId());
        });
        return list;
    }
}
