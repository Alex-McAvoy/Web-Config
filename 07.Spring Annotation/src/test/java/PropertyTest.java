import com.property.beans.Man;
import com.property.config.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description: 属性赋值测试类
 * @Author: Alex McAvoy
 * @Date: 2022/6/27 22:36
 * @Version: 1.0
 **/
public class PropertyTest {
    @Test
    public void testInitAndDestroy() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Man man = applicationContext.getBean(Man.class);
        System.out.println(man);
    }
}
