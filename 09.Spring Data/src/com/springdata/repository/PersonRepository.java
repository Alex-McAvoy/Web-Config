package com.springdata.repository;

import com.springdata.entity.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 *  1.Repository 接口是一个空接口，即一标记接口
 *  2.若定义的接口继承了 Repository，则该接口会被 IOC 容器识别为一个 Repository Bean
 *    纳入到 IOC 容器中，进而可以在该接口中定义满足一定规范的方法
 *  3.也可以通过 @RepositoryDefinition 注解来替继承 Repository 接口
 **/
//@RepositoryDefinition(domainClass = Person.class,idClass = Integer.class)
public interface PersonRepository extends Repository<Person,Integer> {
    /** Repository 定义方法规范
     * 1.查询方法以 find、read、get 开头
     * 2.涉及条件查询时，条件的属性需要用条件关键字（BY、AND 等）连接
     * 3.条件属性首字母大写
     * 4.支持属性的级联查询，若当前类有符合条件的属性，优先采用当前类的属性
     *   若需要使用级联属性，则属性间使用 _ 连接，例如：getByAddress_IdGreaterThan
     * **/
    //根据 name 获取相应 Person
    Person getByName(String name);
    //WHERE name LIKE ?% AND id < ?
    List<Person> getByNameStartingWithAndIdLessThan(String name,Integer id);
    //WHERE name LIKE %? AND id < ?
    List<Person> getByNameEndingWithAndIdLessThan(String name,Integer id);
    //WHERE email IN (?,?,?) OR birth < ?
    List<Person> getByEmailInOrBirthLessThan(List<String> emails, Date birth);

    //级联查询
    //WHERE address.id > ?
    List<Person> getByAddressIdGreaterThan(Integer id);

    //@Query 注解可以自定义 JPQL 语句以实现更灵活的查询
    //查询 id 最大的 Person
    @Query("SELECT p FROM Person p WHERE p.id = (SELECT max(p2.id) FROM Person p2)")
    Person getMaxIdPerson();

    //为 @Query 传递参数的方式1：使用占位符
    @Query("SELECT p FROM Person p WHERE p.name = ?1 AND p.email = ?2")
    List<Person> testQueryAnnotationParams1(String name,String email);

    //为 @Query 传递参数的方式2：命名参数
    @Query("SELECT p FROM Person p WHERE p.name = :name AND p.email = :email")
    List<Person> testQueryAnnotationParams2(@Param("name") String name, @Param("email") String email);

    //对于 LIKE，SpringData 允许在占位符上添加占位号
    @Query("SELECT p FROM Person p WHERE p.name LIKE %?1% OR p.email LIKE %?2%")
    List<Person> testQueryAnnotationLikeParam(String name,String email);

    //设置 nativeQuery=true 即可使用原生的 SQL
    @Query(value = "SELECT count(id) FROM persons",nativeQuery = true)
    long getTotalCount();

    //通过自定义的 JPQL 完成 UPDATE 和 DELETE
    //在 @Query 中，编写 JPQL 必须使用 @Modifying 修饰，以通知 SpringData 这是一个 UPDATE 或 DELETE
    //对于 UPDATE 和 DELETE，需要使用事务，此时需要定义 Service 层，在 Service 层的相应方法中加上 @Transactional 注解
    //默认情况下，SpringData 每个方法都有事务，但该默认事务是只读的
    @Modifying
    @Query("UPDATE Person p SET p.email = :email WHERE id = :id")
    void updatePersonEmail(@Param("id") Integer id,@Param("email") String email);
}
