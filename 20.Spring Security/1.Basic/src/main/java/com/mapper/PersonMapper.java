package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bean.Person;
import org.springframework.stereotype.Repository;

/**
 * 用户 Mapper
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2022/11/3 20:38
 **/
@Repository
public interface PersonMapper extends BaseMapper<Person> {
}
