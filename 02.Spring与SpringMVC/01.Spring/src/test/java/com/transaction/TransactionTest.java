package com.transaction;

import com.jdbc.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 事务测试
 * @author Alex McAvoy
 * @date 2022/2/26 5:39
 * @version 1.0
 **/
public class TransactionTest {
    private ApplicationContext ctx = null;
    {
        ctx = new ClassPathXmlApplicationContext("/config/transactionContext.xml");
    }

    /**事务注解
     * 1. 通过 propagation 指定事务传播息行为，即当前事务方法被另一个事务方法调用时的行为
     *  - @Transactional(propagation = Propagation.REQUIRED): 默认取值，出现异常时两事务都回滚到开始
     *  - @Transactional(propagation = Propagation.REQUIRES_NEW): 事务自己的事务，调用的事务方法的事务被挂起
     * 2. 通过 isolation 指定事务的隔离级别
     *  最常用: 独立提交 @Transactional(isolation = Isolation.READ_COMMITTED)
     * 3. 默认情况下 Spring 的声明式事务对所有运行时异常进行回滚，但也可使用对应属性进行设置，通常取默认值即可
     *  例： noRollbackFor={xxxException.class} 对 xxxException 异常不进行回滚
     * 4. 使用 readOnly 指定事务是否为只读，设为 true 时表示该事务只读取事务但不更新数据，可帮助数据库引擎优化事务
     * 5. 使用 timeout 指定强制回滚之前，事务可占用的时间，单位为秒，即方法超出该时间，会强制回滚
     * **/

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Test
    public void test() {
        System.out.println(1);
    }
}
