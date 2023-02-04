package com.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门类
 * @author Alex McAvoy
 * @date 2022/9/29 13:47
 * @version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
	private Integer id;
	private String departmentName;
}
