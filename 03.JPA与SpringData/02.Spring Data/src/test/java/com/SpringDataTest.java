package com;

import com.repository.PersonCRUDRepository;
import com.repository.PersonPagingAndSortingRepository;
import com.repository.PersonRepository;
import com.entity.Person;
import com.service.PersonService;
import org.aspectj.weaver.ast.Or;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort.Direction;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 测试
 * @author Alex McAvoy
 * @date 2022/3/12 9:53
 * @version 1.0
 **/
public class SpringDataTest {
    private ApplicationContext ctx = null;
    PersonRepository personRepository = null;
    PersonService personService = null;
    PersonCRUDRepository personCRUDRepository = null;
    PersonPagingAndSortingRepository personPagingAndSortingRepository = null;

    {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        personRepository = ctx.getBean(PersonRepository.class);
        personService = ctx.getBean(PersonService.class);
        personCRUDRepository = ctx.getBean(PersonCRUDRepository.class);
        personPagingAndSortingRepository = ctx.getBean(PersonPagingAndSortingRepository.class);
    }

    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void testJPA() {
    }

    /**
     * 根据 name 获取相应 Person
     * @author Alex McAvoy
     * @date 2022/3/13 13:19
     **/
    @Test
    public void testSpringData() {
        Person person = personRepository.getByName("AA");
        System.out.println(person);
    }

    /**
     * 测试 Repository 方法
     * @author Alex McAvoy
     * @date 2022/3/13 13:26
     **/
    @Test
    public void testKeyWords() {
        //WHERE name LIKE ?% AND id < ?
        List<Person> persons = personRepository.getByNameStartingWithAndIdLessThan("C", 10);
        System.out.println(persons);

        //WHERE name LIKE %? AND id < ?
        persons = personRepository.getByNameEndingWithAndIdLessThan("E", 10);
        System.out.println(persons);

        //WHERE email IN (?,?,?) OR birth < ?
        persons = personRepository.getByEmailInOrBirthLessThan(Arrays.asList("AA@163.com", "BB@163.com"), new Date());
        System.out.println(persons);
    }

    /**
     * 级联查询
     * @author Alex McAvoy
     * @date 2022/3/13 14:29
     **/
    @Test
    public void testInterlinkedQuery() {
        //WHERE address.id > ?
        List<Person> persons = personRepository.getByAddressIdGreaterThan(1);
        System.out.println(persons);
    }

    @Test
    public void testQueryAnnotation() {
        Person person = personRepository.getMaxIdPerson();
        System.out.println(person);

        List<Person> persons = personRepository.testQueryAnnotationParams1("AA", "AA@163.com");
        System.out.println(persons);

        persons = personRepository.testQueryAnnotationParams2("BB", "BB@163.com");
        System.out.println(persons);
    }

    @Test
    public void testQueryAnnotationLikeParam() {
        List<Person> persons = personRepository.testQueryAnnotationLikeParam("A", "AA");
        System.out.println(persons);
    }

    @Test
    public void testNativeQuery() {
        long count = personRepository.getTotalCount();
        System.out.println(count);
    }

    @Test
    public void testModifying() {
        personService.updatePersonEmail(1, "XX");
    }

    @Test
    public void testCrudRepository() {
        List<Person> persons = new ArrayList<>();
        for (int i = 'a'; i < 'd'; i++) {
            Person person = new Person();
            person.setName((char) i + "" + (char) i);
            person.setEmail((char) i + "" + (char) i + "@163.com");
            person.setBirth(new Date());
            persons.add(person);
        }
        personService.savePersons(persons);
    }

    @Test
    public void testPagingAndSortingRepository() {
        //PageNumber从0开始
        int pageNumber = 2; //当前页
        int pageSize = 5;  //分页大小
        //Sort封装了排序信息
        Order order1 = new Order(Direction.DESC,"id"); //按id降序
        Order order2 = new Order(Direction.ASC,"email");//按email升序
        Sort sort = new Sort(order1,order2);//按order1排序，order1相同时按order2

        //Pageable接口通常使用其PageRequest实现类，其中封装了分页所需信息
        PageRequest pageable = new PageRequest(pageNumber, pageSize, sort);
        Page<Person> page = personPagingAndSortingRepository.findAll(pageable);

        System.out.println("总记录数: " + page.getTotalElements());
        System.out.println("当前第几页: " + page.getNumber() + 1);
        System.out.println("总页数: " + page.getTotalPages());
        System.out.println("当前页面的 List:" + page.getContent());
        System.out.println("当前页面的记录数:" + page.getNumberOfElements());
    }
}
