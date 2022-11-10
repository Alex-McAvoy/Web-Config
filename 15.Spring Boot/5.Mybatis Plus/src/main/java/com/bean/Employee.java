package com.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 员工类
 * @author Alex McAvoy
 * @date 2022/9/29 13:47
 * @version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @TableId(type = IdType.AUTO)
	private Integer id;
    private String lastName;
    private String email;
    private Integer gender;
    private Integer dId;
}
