package com.autowired.controller;

import com.autowired.service.CupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/6/28 0:02
 * @Version: 1.0
 **/
@Controller
public class CupController {
    @Autowired
    private CupService cupService;
}
