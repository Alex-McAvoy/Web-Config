package com.dao.secondcache;

import com.bean.secondcache.Man;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;


public interface ManMapper {
    public Man getManById(Integer id);
    public void addMan(Man man);

}
