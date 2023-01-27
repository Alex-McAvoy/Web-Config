import com.component.beans.Book;
import com.component.beans.Person;
import com.component.beans.Worker;
import com.component.config.MainConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * 组件注册测试类
 * @author Alex McAvoy
 * @date 2022/6/26 23:10
 * @version 1.0
 **/
public class ComponentTest {

    /**
     * 测试 Spring 的扫描包 @ComponentScan
     * @author Alex McAvoy
     * @date 2022/6/27 0:06
     **/
    @Test
    @SuppressWarnings("resource")
    public void testComponentScan() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }

    /**
     * 测试 Spring 组件注册，@Configuration 和 @Bean 注解
     * @author Alex McAvoy
     * @date 2022/6/26 23:12
     **/
    @Test
    public void testConfiguration() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Book book = applicationContext.getBean(Book.class);
        String[] namesForType = applicationContext.getBeanNamesForType(Person.class);

        System.out.println(book);
        for (String name : namesForType) {
            System.out.println(name);
        }
    }

    /**
     * 测试组件作用域 @Scope
     * @author Alex McAvoy
     * @date 2022/6/27 0:21
     **/
    @Test
    public void testScope() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Person person1 = (Person) applicationContext.getBean("person01");
        Person person2 = (Person) applicationContext.getBean("person01");

        System.out.println(person1);
        System.out.println(person2);
        System.out.println(person1 == person2);
    }

    /**
     * 测试懒加载 @Lazy
     * @author Alex McAvoy
     * @date 2022/6/27 0:33
     **/
    @Test
    public void testLazy() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        System.out.println("IOC 容器创建完成");
        Person person = (Person) applicationContext.getBean("person02");
        System.out.println(person);
    }

    /**
     * 测试条件注册 @Conditional
     * @author Alex McAvoy
     * @date 2022/6/27 1:00
     **/
    @Test
    public void testCondition() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] namesForType = applicationContext.getBeanNamesForType(Worker.class);
        Environment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("os.name");
        System.out.println(property);
        for (String name : namesForType) {
            System.out.println(name);
        }
        Map<String, Worker> workers = applicationContext.getBeansOfType(Worker.class);
        System.out.println(workers);
    }

}
