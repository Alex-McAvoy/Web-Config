package com.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bean.Woman;

public interface WomanMapper extends BaseMapper<Woman> {
    int deleteAll(); //自定义全局操作
}
