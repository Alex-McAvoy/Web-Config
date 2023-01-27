package com;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bean.Employee;
import com.bean.Worker;
import com.mapper.WorkerMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Description: 活动记录 AR 测试
 * @Author: Alex McAvoy
 * @Date: 2022/10/25 17:31
 * @Version: 1.0
 **/
public class TestAR {

    private ApplicationContext iocContext = new
            ClassPathXmlApplicationContext("applicationContext.xml");

    private WorkerMapper workerMapper =
            iocContext.getBean("workerMapper", WorkerMapper.class);

    /**
     * @Description: AR 插入测试
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/25 17:33
     **/
    @Test
    public void testARInsert() {
        Worker worker = new Worker();
        worker.setLastName("123");
        worker.setEmail("123@123.com");
        worker.setGender(1);
        worker.setAge(35);

        boolean result = worker.insert();
        System.out.println("result:" + result);
    }

    /**
     * @Description: AR 修改测试
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/25 17:35
     **/
    @Test
    public void testARUpdate() {
        Worker worker = new Worker();
        worker.setId(4);
        worker.setLastName("456");
        worker.setEmail("456@456.com");
        worker.setGender(1);
        worker.setAge(36);

        boolean result = worker.updateById();
        System.out.println("result:" + result);
    }

    /**
     * @Description: AR 查询测试
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/25 17:36
     **/
    @Test
    public void testARSelect() {
        Worker worker1 = new Worker();
        Worker result1 = worker1.selectById(4);
        System.out.println(result1);

        Worker worker2 = new Worker();
        worker2.setId(4);
        Worker result2 = worker2.selectById();
        System.out.println(result2);

        Worker worker3 = new Worker();
        List<Worker> workers3 = worker3.selectAll();
        System.out.println(workers3);

        Worker worker4 = new Worker();
        List<Worker> workers4 = worker4.selectList(
                new EntityWrapper<Employee>()
                        .like("last_name", "a"));
        System.out.println(workers4);

        Worker worker5 = new Worker();
        Integer result = worker5.selectCount(
                new EntityWrapper<Employee>()
                        .eq("gender", 0));
        System.out.println(result);
    }

    /**
     * @Description: AR 删除测试
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/25 17:41
     **/
    @Test
    public void testARDelete() {

        //删除不存在的数据 逻辑上也是属于成功的
        Worker worker1 = new Worker();
        boolean result1 = worker1.deleteById(9);
        System.out.println(result1);

        Worker worker2 = new Worker();
        worker2.setId(9);
        boolean result2 = worker2.deleteById();
        System.out.println(result2);

        Worker worker3 = new Worker();
        boolean result = worker3.delete(
                new EntityWrapper<Employee>()
                        .like("last_name", "小"));
        System.out.println(result);
    }

    /**
     * @Description: AR 分页复杂操作测试
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/25 17:43
     **/
    @Test
    public void testARPage() {
        Worker worker = new Worker();
        Page<Worker> page = worker.selectPage(
                new Page<>(1, 1),
                new EntityWrapper<Worker>()
                        .like("last_name", "a"));
        List<Worker> workers = page.getRecords();
        System.out.println(workers);
    }
}
