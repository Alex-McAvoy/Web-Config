package com.beans;

import java.util.List;
import java.util.Map;

/**
 * TODO
 * @author Alex McAvoy
 * @date 2022/2/24 1:49
 * @version 1.0
 **/
public class Worker {
    private int id;
    private String name;
    private List<Car> listCars;
    private Map<String, Car> mapCars;

    public Worker() {
    }

    public Worker(int id, String name, List<Car> listCars, Map<String, Car> mapCars) {
        this.id = id;
        this.name = name;
        this.listCars = listCars;
        this.mapCars = mapCars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getListCars() {
        return listCars;
    }

    public void setListCars(List<Car> listCars) {
        this.listCars = listCars;
    }

    public Map<String, Car> getMapCars() {
        return mapCars;
    }

    public void setMapCars(Map<String, Car> mapCars) {
        this.mapCars = mapCars;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                "\n, listCars=" + listCars +
                "\n, mapCars=" + mapCars +
                '}';
    }
}
