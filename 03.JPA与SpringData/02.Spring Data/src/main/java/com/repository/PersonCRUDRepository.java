package com.repository;

import com.entity.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * person CRUD dao
 * @author Alex McAvoy
 * @date 2022/3/13 21:32
 * @version 1.0
 **/
public interface PersonCRUDRepository extends CrudRepository<Person, Integer> {

}
