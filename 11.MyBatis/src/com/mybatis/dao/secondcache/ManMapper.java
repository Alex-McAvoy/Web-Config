package com.mybatis.dao.secondcache;

import com.mybatis.bean.base.Employee;
import com.mybatis.bean.secondcache.Man;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/3/30 16:53
 * @Version: 1.0
 **/
public interface ManMapper {
    public Man getManById(Integer id);
    public void addMan(Man man);

}
