package com.bean;

import lombok.*;

/**
 * @Description: 用户实体
 * @Author: Alex McAvoy
 * @Date: 2022/10/28 22:15
 * @Version: 1.0
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
