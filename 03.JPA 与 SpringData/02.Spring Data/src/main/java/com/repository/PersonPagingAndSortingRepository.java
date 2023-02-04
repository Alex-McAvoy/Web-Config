package com.repository;

import com.entity.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Person 排序 dao
 * @author Alex McAvoy
 * @date 2022/3/14 15:06
 * @version 1.0
 **/
public interface PersonPagingAndSortingRepository extends PagingAndSortingRepository<Person,Integer> {
}
