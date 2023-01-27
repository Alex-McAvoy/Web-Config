import com.aop.beans.MathCalculator;
import com.aop.config.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * AOP 测试类
 * @author Alex McAvoy
 * @date 2022/6/28 0:32
 * @version 1.0
 **/
public class AOPTest {
    @Test
    public void testAOP() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);
        mathCalculator.div(2,2);
    }
}
