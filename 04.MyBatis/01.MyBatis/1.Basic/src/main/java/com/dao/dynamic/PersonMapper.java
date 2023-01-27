package com.dao.dynamic;

import com.bean.dynamic.Person;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonMapper {
    public List<Person> getPersonByConditionIf(Person person);
    public List<Person> getPersonByConditionTrim(Person person);
    public List<Person> getPersonByConditionChoose(Person person);
    public void updateWorkerBySet(Person person);

    public List<Person> getPersonByConditionForeach(@Param("ids")List<Integer> ids);
}
