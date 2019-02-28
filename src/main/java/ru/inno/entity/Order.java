package ru.inno.entity;

import java.sql.Timestamp;

public class Order {
    private int id;
    private Car car;
    private Person owner;
    private Person customer;
    private Timestamp dateOrder;
    private Timestamp begindate;
    private Timestamp enddate;
    private int price;

    public Order(int id, Car car, Person owner, Person customer, Timestamp dateOrder,
                 Timestamp begindate, Timestamp enddate,int price) {
        this.id = id;
        this.car = car;
        this.owner = owner;
        this.customer = customer;
        this.dateOrder = dateOrder;
        this.price = price;
        this.begindate = begindate;
        this.enddate = enddate;
    }

    public Order(int id, Timestamp begindate, Timestamp enddate) {
        this.id = id;
        this.begindate = begindate;
        this.enddate = enddate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Timestamp getBegindate() {
        return begindate;
    }

    public void setBegindate(Timestamp begindate) {
        this.begindate = begindate;
    }

    public Timestamp getEnddate() {
        return enddate;
    }

    public void setEnddate(Timestamp enddate) {
        this.enddate = enddate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    public Timestamp getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Timestamp dateOrder) {
        this.dateOrder = dateOrder;
    }
}
