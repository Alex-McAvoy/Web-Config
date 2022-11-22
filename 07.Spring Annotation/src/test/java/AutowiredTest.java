import com.autowired.beans.Boss;
import com.autowired.config.MainConfig;
import com.autowired.dao.CupDao;
import com.autowired.service.CupService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 自动装配测试类
 * @author Alex McAvoy
 * @date 2022/6/27 22:44
 * @version 1.0
 **/
public class AutowiredTest {
    @Test
    public void testAutowired() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        CupService cupService = applicationContext.getBean(CupService.class);
        System.out.println(cupService);

        CupDao cupDao = (CupDao)applicationContext.getBean("cupDao");
        System.out.println(cupDao);

    }

    @Test
    public void testAutowiredMethod() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Boss boss = applicationContext.getBean(Boss.class);
        System.out.println(boss);
    }
}
