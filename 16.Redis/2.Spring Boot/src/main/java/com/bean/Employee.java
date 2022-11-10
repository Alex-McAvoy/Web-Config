package com.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 员工类
 * - 在使用 Redis 缓存机制时，需要使用序列化机制
 * @author Alex McAvoy
 * @date 2022/9/29 13:47
 * @version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
	private Integer id;
    private String lastName;
    private String email;
    private Integer gender;
    private Integer dId;
}
