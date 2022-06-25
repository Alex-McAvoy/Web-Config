package com.jpa.entity.bionetoone;

import javax.persistence.*;

/**
 * @Description: 双向一对一关系实体类
 * @Author: Alex McAvoy
 * @Date: 2022/3/11 14:18
 * @Version: 1.0
 **/

@Table(name = "workers")
@Entity
public class Worker {
    @GeneratedValue
    @Id
    private Integer id;
    @Column(name = "worker_name")
    private String workerName;
    //对于不维护关联关系，即没有外键的一方，性需要设置 mappedBy 属性
    @OneToOne(mappedBy = "worker")
    private Work work;;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }
}
