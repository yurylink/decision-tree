package com.yurylink.decisiontree.common.model;

import java.time.LocalDate;

public class Promotion {

    private int type;
    private String name;
    private LocalDate due;

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

    public LocalDate getDue() {
        return due;
    }

    public void setDue(LocalDate due) {
        this.due = due;
    }
}
