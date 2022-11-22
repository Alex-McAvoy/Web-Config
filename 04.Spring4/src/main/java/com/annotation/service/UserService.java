package com.annotation.service;

import com.annotation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 模拟业务层
 * @author Alex McAvoy
 * @date 2022/2/25 0:44
 * @version 1.0
 **/
@Service
public class UserService {
    private UserRepository userRepository;

    //当容器中存在多个类型的兼容 Bean 时,通过自动装配可能会抛出异常
    //此时使用 @Qualifier 提供 Bean 的名称,使 Spring 使用入参的方法注入 Bean
    @Autowired
    public void  setUserRepository(@Qualifier("userRepositoryImpl")UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    public void add() {
        System.out.println("UserService");
        userRepository.save();
    }
}
