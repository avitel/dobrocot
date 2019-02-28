package ru.inno.entity;

import javax.persistence.*;

@Entity
@Table(name = "mark")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Mark(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Mark(String name) {
        this.name = name;
    }

    public Mark() {}

    public void setName(String name) {
        this.name = name;
    }


}
