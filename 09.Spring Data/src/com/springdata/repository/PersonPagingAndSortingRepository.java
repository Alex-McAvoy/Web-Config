package com.springdata.repository;

import com.springdata.entity.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/3/14 15:06
 * @Version: 1.0
 **/
public interface PersonPagingAndSortingRepository extends PagingAndSortingRepository<Person,Integer> {
}
