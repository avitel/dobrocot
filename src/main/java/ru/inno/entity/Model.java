package ru.inno.entity;

public class Model {
    int id;
    Mark mark;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mark getId_mark() {
        return mark;
    }

    public void setId_mark(Mark mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
  
    public Model(int id, Mark mark, String name) {
        this.id = id;
        this.mark = mark;
        this.name = name;  

    public Model() {
    }
}
