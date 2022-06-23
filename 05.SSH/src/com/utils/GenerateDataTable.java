package com.utils;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * @Description: 根据 hbm.xml 生成数据表
 * @Author: Alex McAvoy
 * @Date: 2022/2/27 22:12
 * @Version: 1.0
 **/
public class GenerateDataTable {
    public static void main(String[] args) {
        //读取配置文件
        Configuration cfg = new Configuration().configure("/conf/utils/generateDataTable.cfg.xml");
        //生成ddl文件，并在控制台输出
        SchemaExport export = new SchemaExport(cfg);
        export.create(true, true);
    }
}
