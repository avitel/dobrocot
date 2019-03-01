package ru.inno.entity;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity
@Table(name = "_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car")
    private Car car;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller")
    private Person owner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer")
    private Person customer;

    @Column(name = "date")
    private Timestamp dateOrder;
    @Column
    private Timestamp begindate;
    @Column
    private Timestamp enddate;
    @Column
    private int price;

    public Order() {
    }

    public Order(int id, Car car, Person owner, Person customer, Timestamp dateOrder) {
        this.id = id;
        this.car = car;
        this.owner = owner;
        this.customer = customer;
        this.dateOrder = dateOrder;
    }

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

    public Order(Car car, Person owner, Person customer, Timestamp dateOrder, Timestamp begindate, Timestamp enddate, int price) {
        this.car = car;
        this.owner = owner;
        this.customer = customer;
        this.dateOrder = dateOrder;
        this.begindate = begindate;
        this.enddate = enddate;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
