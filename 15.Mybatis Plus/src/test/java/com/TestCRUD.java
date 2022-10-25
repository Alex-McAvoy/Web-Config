package com;

import com.baomidou.mybatisplus.plugins.Page;
import com.bean.Employee;
import com.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 通用CRUD测试
 * @Author: Alex McAvoy
 * @Date: 2022/10/25 16:32
 * @Version: 1.0
 **/
public class TestCRUD {
    private ApplicationContext iocContext = new
            ClassPathXmlApplicationContext("applicationContext.xml");

    private EmployeeMapper employeeMapper =
            iocContext.getBean("employeeMapper",EmployeeMapper.class);


    /**
     * @Description: 通用插入测试
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/25 15:43
     **/
    @Test
    public void testCommonInsert() {

        Employee employee  = new Employee();
        employee.setLastName("MP");
        employee.setEmail("mp@123.com");
        employee.setGender(1);
        employee.setAge(22);
        employee.setSalary(20000.0);

        /** insert()方法在插入时，会根据实体类的每个属性进行非空判断，
         *  只有非空的属性对应的字段才会出现到SQL语句中
         * **/
        Integer result = employeeMapper.insert(employee);
        System.out.println("result:" + result);

        //获取当前数据在数据库中的主键值
        Integer key = employee.getId();
        System.out.println("key:" + key );

        /** insertAllColumn() 方法在插入时， 不管属性是否非空，
         *  属性所对应的字段都会出现到SQL语句中
         **/
        result = employeeMapper.insertAllColumn(employee);
        System.out.println("result: " + result);
    }

    /**
     * @Description: 通用更新测试
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/25 16:07
     **/
    @Test
    public void testCommonUpdate() {
        Employee employee = new Employee();
        employee.setId(7);
        employee.setLastName("aaa");
        employee.setEmail("aaa@qq.com");
        employee.setGender(0);
        employee.setAge(33);

        /** updateById()、updateAllColumnById() 与 insert()、insertAllColumn() 相似
         *  updateById() 会根据实体类的每个属性进行非空判断， 只有非空的属性对应的字段才会出现到SQL语句中
         *  updateAllColumnById() 不管属性是否非空， 属性所对应的字段都会出现到SQL语句中
         *  **/
        Integer result = employeeMapper.updateById(employee);
//        Integer result = employeeMapper.updateAllColumnById(employee);
        System.out.println("result:" + result);
    }

    /**
     * @Description: 通用查询测试
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/25 16:10
     **/
    @Test
    public void  testCommonSelect() {
        /** 1. 通过id查询 **/
        Employee employee1 = employeeMapper.selectById(7);
        System.out.println("通过id查询: " +employee1);

        /** 2. 通过多个列查询 **/
        //通过: id、lastName 查询，封装后会充当为条件，进行查询
        Employee employee2 = new Employee();
        employee2.setId(7);
        employee2.setLastName("aaa");
        //若查到多条数据时，会报错
        Employee result = employeeMapper.selectOne(employee2);
        System.out.println("通过多个列查询: " + result);

        /** 3. 通过多个id查询 **/
        List<Integer> idList = new ArrayList<>();
        idList.add(4);
        idList.add(5);
        idList.add(6);
        idList.add(7);
        List<Employee> employees1 = employeeMapper.selectBatchIds(idList);
        System.out.println("通过多个id查询: " + employees1);

        /** 4. 通过Map封装条件查询 **/
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("last_name", "Tom");
        columnMap.put("gender", 1);
        List<Employee> employees2 = employeeMapper.selectByMap(columnMap);
        System.out.println("通过Map封装条件查询: " + employees2);

        /** 5. 分页查询 **/
        List<Employee> employees3 = employeeMapper.selectPage(new Page<>(3, 2), null);
        System.out.println("分页查询: " + employees3);
    }

    /**
     * @Description: 通用删除测试
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/25 16:22
     **/
    @Test
    public void testCommonDelete() {
        /** 1.根据id删除 **/
        Integer result1 = employeeMapper.deleteById(8);
        System.out.println("根据id删除: " + result1);

        /** 2.根据条件删除 **/
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("last_name", "MP");
        columnMap.put("email", "mp@atguigu.com");
        Integer result2 = employeeMapper.deleteByMap(columnMap);
        System.out.println("根据条件删除: " + result2);

        /** 3.批量删除 **/
        List<Integer> idList = new ArrayList<>();
        idList.add(3);
        idList.add(4);
        idList.add(5);
        Integer result3 = employeeMapper.deleteBatchIds(idList);
        System.out.println("批量删除: " + result3);
    }

}
