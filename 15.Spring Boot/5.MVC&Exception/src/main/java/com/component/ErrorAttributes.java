package com.component;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 给容器中添加自定义的 ErrorAttribute
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2022/9/22 17:13
 **/
@Component
public class ErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, options);
        //自定义的异常处理器携带的数据
        Map<String, Object> ext = (Map<String, Object>) webRequest.getAttribute("ext", 0);
        //返回值的map就是页面和json能获取的所有字段
        map.put("ext", ext);
        return map;
    }
}
