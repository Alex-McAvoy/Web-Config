package com.springdata.repository;

import com.springdata.entity.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/3/13 21:32
 * @Version: 1.0
 **/
public interface PersonCRUDRepository extends CrudRepository<Person, Integer> {

}
