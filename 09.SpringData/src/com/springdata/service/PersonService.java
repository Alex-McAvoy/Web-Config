package com.springdata.service;

import com.springdata.repository.PersonRepository;
import com.springdata.entity.Person;
import com.springdata.repository.PersonCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/3/13 21:22
 * @Version: 1.0
 **/
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonCRUDRepository personCRUDRepository;

    @Transactional
    public void updatePersonEmail(Integer id,String email){
        personRepository.updatePersonEmail(id,email);
    }

    @Transactional
    public void savePersons(List<Person> persons) {
        personCRUDRepository.save(persons);
    }


}
