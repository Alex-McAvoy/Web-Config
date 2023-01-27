package com.controller;

import com.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 任务控制器
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2022/10/17 21:18
 **/
@Controller
public class TaskController {
    @Autowired
    TaskService taskService;

    /**
     * 异步任务
     *
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/11/9 22:45
     **/
    @GetMapping("/asyncTask")
    public String asyncTask() {
        taskService.async();
        return "task/async";
    }

    /**
     * 同步任务
     *
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/11/9 22:45
     **/
    @GetMapping("/scheduledTask")
    public String scheduledTask() {
        taskService.schedule();
        return "task/schedule";
    }

    /**
     * 邮件任务
     *
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/11/9 22:45
     **/
    @GetMapping("/emailTask")
    public String emailTask() throws Exception {
        taskService.email();
        return "task/email";
    }

}
