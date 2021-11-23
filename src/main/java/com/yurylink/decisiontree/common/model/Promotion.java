package com.yurylink.decisiontree.common.model;

import java.util.Date;

public class Promotion {

    private int type;
    private String name;
    private Date due;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }
}
