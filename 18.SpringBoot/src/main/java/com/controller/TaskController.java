package com.controller;

import com.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 任务控制器
 * @Author: Alex McAvoy
 * @Date: 2022/10/17 21:18
 * @Version: 1.0
 **/
@Controller
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("/asyncTask")
    public String asyncTask() {
        taskService.async();
        return "task/async";
    }

    @GetMapping("/scheduledTask")
    public String scheduledTask() {
        taskService.schedule();
        return "task/schedule";
    }

    @GetMapping("/emailTask")
    public String emailTask() throws Exception {
        taskService.email();
        return "task/email";
    }

}
