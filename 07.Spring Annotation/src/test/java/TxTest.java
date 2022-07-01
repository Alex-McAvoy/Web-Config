import com.tx.config.MainConfig;
import com.tx.service.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description: 声明式事务测试类
 * @Author: Alex McAvoy
 * @Date: 2022/7/1 22:37
 * @Version: 1.0
 **/
public class TxTest {
    @Test
    public void testAOP() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        UserService userService = applicationContext.getBean(UserService.class);
        userService.insertUser();
    }
}
