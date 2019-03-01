package ru.inno.entity;

import javax.persistence.*;

@Entity
@Table(name = "model")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mark")
    private Mark mark;

    @Column
    private String name;

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
    }

    public Model(Mark mark, String name) {
        this.mark = mark;
        this.name = name;
    }

    public Model() {
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", mark=" + mark +
                ", name='" + name + '\'' +
                '}';
    }
}
