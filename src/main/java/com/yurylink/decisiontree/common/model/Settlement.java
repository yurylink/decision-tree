package com.yurylink.decisiontree.common.model;

import java.math.BigDecimal;

public class Settlement {

    private int id;
    private String name;
    private BigDecimal totalValue;
    private BigDecimal nextPayment;

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

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public BigDecimal getNextPayment() {
        return nextPayment;
    }

    public void setNextPayment(BigDecimal nextPayment) {
        this.nextPayment = nextPayment;
    }
}
