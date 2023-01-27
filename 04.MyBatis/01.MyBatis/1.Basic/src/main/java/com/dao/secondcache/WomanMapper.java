package com.dao.secondcache;

import com.bean.secondcache.Woman;

public interface WomanMapper {
    public Woman getWomanById(Integer id);
    public void addWoman(Woman woman);

}
