import com.aop.beans.MathCalculator;
import com.aop.config.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description: AOP 测试类
 * @Author: Alex McAvoy
 * @Date: 2022/6/28 0:32
 * @Version: 1.0
 **/
public class AOPTest {
    @Test
    public void testAOP() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);
        mathCalculator.div(2,2);
    }
}
