package ru.inno.entity;

public class Engine {
    int id;
    String name;

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


    public Engine(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
