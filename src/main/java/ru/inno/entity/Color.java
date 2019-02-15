package ru.inno.entity;

public class Color {
    private String name;

    private int id;

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

    public Color() {
    }

    public Color(int id, String name) {
        this.name = name;
        this.id = id;
    }
}
