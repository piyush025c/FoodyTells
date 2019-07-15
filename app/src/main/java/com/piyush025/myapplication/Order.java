package com.piyush025.myapplication;

public class Order {

    String id;
    String orderSummary;
    String orderAmount;

    public Order()
    {

    }

    public Order(String id,String orderSummary,String amount)
    {
        this.id=id;
        this.orderSummary=orderSummary;
        this.orderAmount=amount;
    }

    public String getId() {
        return id;
    }

    public String getOrderSummary() {
        return orderSummary;
    }

    public String getOrderAmount() {
        return orderAmount;
    }
}
