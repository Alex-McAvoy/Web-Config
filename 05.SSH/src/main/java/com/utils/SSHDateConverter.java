package com.utils;

import org.apache.struts2.util.StrutsTypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 类型转换器
 * @author Alex McAvoy
 * @date 2022/2/28 3:12
 * @version 1.0
 **/
public class SSHDateConverter extends StrutsTypeConverter {
    private DateFormat dateFormat;

    {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public Object convertFromString(Map map, String[] strings, Class aClass) {
        if(aClass == Date.class) {
            try {
                return dateFormat.parse(strings[0]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String convertToString(Map map, Object o) {
        if (o instanceof Date) {
            return dateFormat.format((Date)o);
        }
        return null;
    }
}
