package com.autowired.service;

import com.autowired.dao.CupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * TODO
 * @author Alex McAvoy
 * @date 2022/6/28 0:01
 * @version 1.0
 **/
@Service
public class CupService {
//    @Qualifier("cupDao2")
    @Autowired
    private CupDao cupDao;

    public void print() {
        System.out.println(cupDao);
    }

    @Override
    public String toString() {
        return "CupService{" +
                "cupDao=" + cupDao +
                '}';
    }
}
