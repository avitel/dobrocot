package ru.inno.entity;

import java.sql.Timestamp;

public class Car {
    private int id;
    private Person owner;
    private Mark mark;
    private Model model;
    private Timestamp assembledate;
    private Engine engine;
    private int numberofseats;
    private Color color;
    private int dayprice;

    public Car(int id, Person owner, Mark mark, Model model, Timestamp assembledate, Engine engine, int numberofseats, Color color
            , int dayprice
    ) {
        this.id = id;
        this.owner = owner;
        this.mark = mark;
        this.model = model;
        this.assembledate = assembledate;
        this.engine = engine;
        this.numberofseats = numberofseats;
        this.color = color;
        this.dayprice = dayprice;
    }

    public int getDayprice() {
        return dayprice;
    }

    public void setDayprice(int dayprice) {
        this.dayprice = dayprice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Timestamp getAssembledate() {
        return assembledate;
    }

    public void setAssembledate(Timestamp assembledate) {
        this.assembledate = assembledate;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public int getNumberofseats() {
        return numberofseats;
    }

    public void setNumberofseats(int numberofseats) {
        this.numberofseats = numberofseats;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
