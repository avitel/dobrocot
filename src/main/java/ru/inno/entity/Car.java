package ru.inno.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Person owner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mark_id")
    private Mark mark;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id")
    private Model model;

    @Column
    private Timestamp assembledate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @Column
    private int numberofseats;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "color_id")
    private Color color;

    @Column
    private int dayprice;

    @Column
    private boolean isdeleted;

    public Car() {}

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

    public Car(Person owner, Mark mark, Model model, Timestamp assembledate, Engine engine, int numberofseats, Color color, int dayprice) {
        this.owner = owner;
        this.mark = mark;
        this.model = model;
        this.assembledate = assembledate;
        this.engine = engine;
        this.numberofseats = numberofseats;
        this.color = color;
        this.dayprice = dayprice;
    }

    public void setIsdeleted(boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

    public int getId() {
        return id;
    }

    public Person getOwner() {
        return owner;
    }

    public Mark getMark() {
        return mark;
    }

    public Model getModel() {
        return model;
    }

    public Timestamp getAssembledate() {
        return assembledate;
    }

    public Engine getEngine() {
        return engine;
    }

    public int getNumberofseats() {
        return numberofseats;
    }

    public Color getColor() {
        return color;
    }

    public int getDayprice() {
        return dayprice;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", owner=" + owner +
                ", mark=" + mark +
                ", model=" + model +
                ", assembledate=" + assembledate +
                ", engine=" + engine +
                ", numberofseats=" + numberofseats +
                ", color=" + color +
                ", dayprice=" + dayprice +
                '}';
    }
}
