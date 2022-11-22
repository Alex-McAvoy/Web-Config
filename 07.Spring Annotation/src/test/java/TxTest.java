import com.tx.config.MainConfig;
import com.tx.service.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 声明式事务测试类
 * @author Alex McAvoy
 * @date 2022/7/1 22:37
 * @version 1.0
 **/
public class TxTest {
    @Test
    public void testAOP() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        UserService userService = applicationContext.getBean(UserService.class);
        userService.insertUser();
    }
}
