package ru.inno.entity;

public class Person {

    private int id;
    private String name;
    private String login;
    private String password;
    private String role;

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

    public Person(int id, String name, String login, String role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.role = role;
    }

    public Person(int id, String name, String login) {
        this.id = id;
        this.name = name;
        this.login = login;
    }
}
