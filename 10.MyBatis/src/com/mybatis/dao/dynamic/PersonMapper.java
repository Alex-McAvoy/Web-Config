package com.mybatis.dao.dynamic;

import com.mybatis.bean.dynamic.Person;
import org.apache.ibatis.annotations.Param;

import java.util.Arrays;
import java.util.List;
/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/4/3 19:12
 * @Version: 1.0
 **/
public interface PersonMapper {
    public List<Person> getPersonByConditionIf(Person person);
    public List<Person> getPersonByConditionTrim(Person person);
    public List<Person> getPersonByConditionChoose(Person person);
    public void updateWorkerBySet(Person person);

    public List<Person> getPersonByConditionForeach(@Param("ids")List<Integer> ids);
}
