package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Description: 异步任务、定时任务
 * @Author: Alex McAvoy
 * @Date: 2022/10/17 21:32
 * @Version: 1.0
 **/
@Service
public class TaskService {

    @Autowired
    JavaMailSenderImpl mailSender;

    /**
     * @Description: 异步任务
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/17 21:34
     **/
    @Async
    public void async() {
        System.out.println("页面加载完成");
        System.out.println("处理数据中...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("处理数据完成...");
    }

    /** @Scheduled(cron = "second minute hour day month week") 开启定时任务
     *  - 秒，分，时，日，月，周几
     *  - 用空格分割
     *  - * 代表任意时刻，- 代表区间， , 代表选择枚举， / 代表步长
     *  - L 代表最后，W 代表工作日，# 代表星期，？代表日与星期冲突匹配
     *  - 周几用缩写，或数字
     *  例如：
     *  【0 0/5 14,18 * * ?】 每天14点整，和18点整，每隔5分钟执行一次
     *  【0 15 10 ? * 1-6】 每个月的周一至周六10:15分执行一次
     *  【0 0 2 ? * 6L】每个月的最后一个周六凌晨2点执行一次
     *  【0 0 2 LW * ?】每个月的最后一个工作日凌晨2点执行一次
     *  【0 0 2-4 ? * 1#1】每个月的第一个周一凌晨2点到4点期间，每个整点都执行一次；
     * **/
    @Scheduled(cron = "1-5 * * * * MON-SAT")
    public void schedule() {
        System.out.println("执行定时任务");
    }

    public void email() throws Exception{
        //简单邮件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("邮件标题");
        message.setText("邮件内容");
        message.setTo("收信人@foxmail.com");
        message.setFrom("发信人@qq.com");
        mailSender.send(message);
        System.out.println("简单邮件发送成功");

        //复杂消息邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("邮件标题");
        helper.setText("<b style='color:red'>邮件内容</b>",true); // true为开启H5C3
        helper.setTo("收信人@foxmail.com");
        helper.setFrom("发信人@qq.com");
        helper.addAttachment("1.jpg",new File("附件路径"));
        mailSender.send(message);
        System.out.println("复杂消息邮件发送成功");
    }
}
