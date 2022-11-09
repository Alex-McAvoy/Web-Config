package com.bean;

/**
 * 用户实体类
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2022/11/3 20:36
 **/
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private Integer id;
    private String username;
    private String password;
    private String authority;
    private String role;
}
