package com.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 生产者-支付模块实体类
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2023/1/31 11:23
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@TableName("payment")
public class Payment implements Serializable {
    @TableId(value = "payment_id",type = IdType.AUTO)
    private Integer id;
    @TableField("payment_serial")
    private String serial;
    @TableField("db_source")
    private String dbSource;
}
