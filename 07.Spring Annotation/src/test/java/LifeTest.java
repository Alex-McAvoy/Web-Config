import com.life.config.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 生命周期测试类
 * @author Alex McAvoy
 * @date 2022/6/27 1:16
 * @version 1.0
 **/
public class LifeTest {

    @Test
    public void testInitAndDestroy() {
        //容器创建
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        System.out.println("容器创建完成");
        //容器销毁
        applicationContext.close();
    }
}
