package com.redis;

import java.io.Serializable;

/**
 * Created by xx on 2017-07-21.
 */
public class User implements Serializable{
    private String name;
    private  String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
