package com.mybatis.dao.secondcache;

import com.mybatis.bean.secondcache.Woman;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/3/30 16:53
 * @Version: 1.0
 **/
public interface WomanMapper {
    public Woman getWomanById(Integer id);
    public void addWoman(Woman woman);

}
