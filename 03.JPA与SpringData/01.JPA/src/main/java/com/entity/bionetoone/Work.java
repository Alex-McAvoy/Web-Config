package com.entity.bionetoone;

import javax.persistence.*;

/**
 *  双向一对一关系实体
 * @author Alex McAvoy
 * @date 2022/3/11 14:19
 * @version 1.0
 **/
@Table(name = "works")
@Entity
public class Work {
    @GeneratedValue
    @Id
    private Integer id;
    @Column(name = "work_name")
    private String workName;
    //使用 @OneToOne 来映射 1-1 关系
    //若要在当前数据表中添加主键，则需要使用 @JoinColumn 注解，
    //1-1 关系需要设置 unique=true 属性
    @JoinColumn(name = "worker_id",unique = true)
    @OneToOne
    private Worker worker;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
}
