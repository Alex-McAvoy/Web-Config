import com.crud.bean.Employee;
import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/4/20 20:24
 * @Version: 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:dispatcherServlet.xml"})
public class MVCTest {
    @Autowired
    WebApplicationContext webApplicationContext; //SpringMVC的IOC

    MockMvc mockMvc; //虚拟MVC，获取处理结果

    @Before
    public void initMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testPage() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.
                get("/emps").param("pn", "5"))
                .andReturn(); //模拟请求拿到返回值

        MockHttpServletRequest request = mvcResult.getRequest();
        PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");

        System.out.println("当前页码：" + pageInfo.getPageNum());
        System.out.println("总页码：" + pageInfo.getPages());
        System.out.println("总记录数：" + pageInfo.getTotal());
        System.out.print("在页面连续显示的页码：");
        int[] nums = pageInfo.getNavigatepageNums();
        for (int i : nums)
            System.out.print(i + " ");
        System.out.println();

        List<Employee> list = pageInfo.getList();
        for (Employee employee : list) {
            System.out.println("ID："+employee.getEmpId()+"==>DepartmentId:"+employee.getDepartment());
        }
    }
}
