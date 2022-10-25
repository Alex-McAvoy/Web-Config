package com;

import com.baomidou.mybatisplus.plugins.Page;
import com.bean.Man;
import com.mapper.ManMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Description: 插件测试
 * @Author: Alex McAvoy
 * @Date: 2022/10/25 17:31
 * @Version: 1.0
 **/
public class TestPlugin {

    private ApplicationContext iocContext = new
            ClassPathXmlApplicationContext("applicationContext.xml");

    private ManMapper manMapper =
            iocContext.getBean("manMapper",ManMapper.class);

    /**
     * @Description: 分页插件测试
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/25 19:21
     **/
    @Test
    public void testPage() {
        Page<Man> page = new Page<>(1,1);
        List<Man> men = manMapper.selectPage(page, null);
        System.out.println(men);

        System.out.println("===============获取分页相关信息======================");
        System.out.println("总条数:" +page.getTotal());
        System.out.println("当前页码: "+  page.getCurrent());
        System.out.println("总页码:" + page.getPages());
        System.out.println("每页显示的条数:" + page.getSize());
        System.out.println("是否有上一页: " + page.hasPrevious());
        System.out.println("是否有下一页: " + page.hasNext());

        //将查询的结果封装到page对象中
        page.setRecords(men);
    }

    /**
     * @Description: SQL执行分析插件测试，分析 DELETE、UPDATE，防止对全表恶意删除、修改
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/25 19:26
     **/
    @Test
    public void testSQLExplain() {
        manMapper.delete(null);  //全表删除
    }

    /**
     * @Description: 性能分析插件测试，输出每条 SQL 执行时间
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/25 19:27
     **/
    @Test
    public void testPerformance() {
        Man man = new Man();
        man.setLastName("aaa");
        man.setEmail("aaa@aaa.com");
        man.setGender(0);
        man.setAge(22);

        manMapper.insert(man);
    }

    /**
     * @Description: 乐观锁插件测试
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/25 19:30
     **/
    @Test
    public void testOptimisticLocker() {
        //更新操作
        Man man = new Man();
        man.setId(1);
        man.setLastName("AA");
        man.setEmail("AA@AA.com");
        man.setGender(1);
        man.setAge(22);

        /** 乐观锁
         *  - 使用乐观锁，需要在相应的 Bean、数据表中加入 version 字段
         *  - 同时，在 Bean 的 version 字段上加入 @Version
         *  - 若更新时，version 值与数据库中的不匹配，则拒绝更新
         *  - 若更新成功，则该字段在数据库中会自增
         * **/
        man.setVersion(3);

        manMapper.updateById(man);
    }
}
