package com.annotation.repository;

import com.annotation.TestObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 模拟持久化层
 * @author Alex McAvoy
 * @date 2022/2/25 0:42
 * @version 1.0
 **/

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

    //默认情况下,使用 Autowired 注解的属性都需要被设置,当容器找不到匹配的 Bean 设置属性时会抛出异常
    //此时可以设为 false,使得找不到匹配的属性设为 null
    @Autowired(required = false)
    private TestObject testObject;

    @Override
    public void save() {
        System.out.println("UserRepository");
        System.out.println(testObject);
    }
}
