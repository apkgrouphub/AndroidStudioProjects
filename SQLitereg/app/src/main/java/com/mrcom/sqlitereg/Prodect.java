package com.mrcom.sqlitereg;

/**
 * Created by PACHU on 28-01-2017.
 */

public class Prodect {
    private String name;
    private String age;

    public Prodect(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
