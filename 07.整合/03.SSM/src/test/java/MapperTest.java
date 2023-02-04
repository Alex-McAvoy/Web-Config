import com.bean.Department;
import com.bean.Employee;
import com.dao.DepartmentMapper;
import com.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * 测试 Dao 层
 * @author Alex McAvoy
 * @date 2022/4/16 18:51
 * @version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {

    @Autowired(required = false)
    DepartmentMapper departmentMapper;
    @Autowired(required = false)
    EmployeeMapper employeeMapper;
    @Autowired
    SqlSession sqlSession;

    @Test
    public void testInsertDepartment() {
        departmentMapper.insertSelective(new Department(null, "开发部"));
        departmentMapper.insertSelective(new Department(null, "测试部"));
        departmentMapper.insertSelective(new Department(null, "后勤部"));
        departmentMapper.insertSelective(new Department(null, "财务部"));
    }

    @Test
    public void testInsertEmployee() {
        employeeMapper.insertSelective(new Employee(null, "AA", "M", "AA@163.com", 1));
        employeeMapper.insertSelective(new Employee(null, "BB", "F", "BB@163.com", 2));
        employeeMapper.insertSelective(new Employee(null, "CC", "M", "CC@163.com", 1));
        employeeMapper.insertSelective(new Employee(null, "DD", "F", "DD@163.com", 3));
    }

    @Test
    public void testBatchInsertEmployee() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 50; i++) {
            String uid = UUID.randomUUID().toString().substring(0,5) + i;
            employeeMapper.insertSelective(new Employee(null,uid,"M",uid + "@163.com",4));
        }
    }
}
