package com.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import com.bean.Employee;
import com.dao.EmployeeDao;
import com.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Employee 的 Service 实现类
 * - 全局缓存 @CacheConfig: 当使用缓存时，可使用该注解来设置全局规则，这样方法上的规则可以省略不写
 *   另外，当缓存规则较为复杂时，可在方法上使用 @Caching 注解来组合使用 @Cacheable、@CachePut、@CacheEvict
 * @author Alex McAvoy
 * @date 2022/9/29 21:08
 * @version 1.0
 **/
//@CacheConfig(cacheNames = "employee")
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired(required = false)
    EmployeeDao employeeDao;

    /**
     * 结果缓存：@Cacheable 将方法的运行结果缓存，以后再要相同的数据，直接从缓存中获取
     * 属性：
     * - cacheNames/value: CacheManager 管理多个 Cache 组件，对于缓存的真正 CRUD 操作在 Cache 组件中，
     * 每一个缓存都有一唯一的名字指定缓存名，该方法可指定缓存组件的名字
     * - key: 缓存数据使用的 key，默认使用方法的参数值，支持 SpELl 表达式
     * 参数名 #id == #root.args[0]
     * - kegGenerator: key 的生成器，可指定 key 的生成器的组件 id，与 key 二选一使用
     * - cacheManager: 指定缓存管理器
     * - cacheResolver: 指定缓存解析器，与 cacheManager 二选一使用
     * - condition: 符合条件的情况下进行缓存，支持 SpELl 表达式，例 #id>0
     * - unless: 否定缓存，当 unless 指定条件为 true 时，方法的返回值不会被缓存
     * 与 condition 相反，但其可使用获取到结果来判断
     * - sync: 是否使用异步模式，默认为 false，改为 true 后，unless 不再支持
     **/
    @Cacheable(cacheNames = "employee", key = "#id")
    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeDao.selectById(id);
    }

    /**
     * 删除缓存: @CacheEvict 在缓存删除时，同步删除缓存数据
     **/
    @CacheEvict(cacheNames = "department",key = "#id")
    @Override
    public void deleteEmployeeById(Integer id) {
        employeeDao.deleteById(id);
    }

    /**
     * 更新缓存：@CachePut 修改数据库数据，同时更新缓存数据
     * - 运行时机：先调用目标方法，再将目标方法的结果缓存
     * - 默认情况下，存入缓存的 key 为传入的对象，value 为返回的对象，故而不允许方法返回为空值
     * 同时，使用 #result 可以获取返回的对象
     * - 因此，需要指定 key，以便与缓存中的数据进行同步
     **/
    @CachePut(cacheNames = "employee", key = "#employee.id")
    @Override
    public void updateEmployee(Employee employee) {
        employeeDao.updateById(employee);
    }

    @Override
    public void insertEmployee(Employee employee) {
        employeeDao.insert(employee);
    }

    @CachePut(cacheNames = "employees",keyGenerator = "employeesKeyGenerator")
    @Override
    public List<Employee> getEmployeeList() {
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        return employeeDao.selectList(queryWrapper);
    }

}
