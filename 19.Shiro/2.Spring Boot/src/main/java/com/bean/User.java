package com.bean;

import lombok.*;

/**
 * 用户实体
 * @author Alex McAvoy
 * @date 2022/10/28 22:15
 * @version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String password;
    private Integer rid;
}
