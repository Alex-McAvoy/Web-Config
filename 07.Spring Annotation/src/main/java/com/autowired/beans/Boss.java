package com.autowired.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TODO
 * @author Alex McAvoy
 * @date 2022/6/28 0:21
 * @version 1.0
 **/
@Component
public class Boss {
    @Autowired
    private Cup cup;

    public Boss() {
    }

//    @Autowired
    public Boss(Cup cup) {
        this.cup = cup;
    }

    public Cup getCup() {
        return cup;
    }

//    @Autowired
    public void setCup(Cup cup) {
        this.cup = cup;
    }

    @Override
    public String toString() {
        return "Boos{" +
                "cup=" + cup +
                '}';
    }
}
