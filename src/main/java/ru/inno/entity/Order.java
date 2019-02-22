package ru.inno.entity;

import java.sql.Timestamp;

public class Order {
    private int id;
    private Car car;
    private Person owner;
    private Person customer;
    private Timestamp dateOrder;

    public Order(int id, Car car, Person owner, Person customer, Timestamp dateOrder) {
        this.id = id;
        this.car = car;
        this.owner = owner;
        this.customer = customer;
        this.dateOrder = dateOrder;
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
