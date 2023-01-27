package com.bean.cascade;

import java.util.List;

public class Department {
    private Integer id;
    private String name;
    private List<Worker> workers;

    public Department() {
    }

    public Department(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Department(Integer id, String name, List<Worker> workers) {
        this.id = id;
        this.name = name;
        this.workers = workers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", workers=" + workers +
                '}';
    }
}
