package ru.inno.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CarBuilder {

    private Car car;

    public CarBuilder setId(int id){
        car.setId(id);
        return this;
    }

    public CarBuilder setPerson(Person person){
        car.setOwner(person);
        return this;
    }

    public CarBuilder setMark(Mark mark){
        car.setMark(mark);
        return this;
    }

    public CarBuilder setModel(Model model){
        car.setModel(model);
        return this;
    }

    public CarBuilder setAssembleDate(Timestamp ts){
        car.setAssembledate(ts);
        return this;
    }

    public CarBuilder setEngine(Engine engine){
        car.setEngine(engine);
        return this;
    }

    public CarBuilder setSeats(int nuwOfSeats){
        car.setNumberofseats(nuwOfSeats);
        return this;
    }

    public CarBuilder setColor(Color color){
        car.setColor(color);
        return this;
    }

    public Car getCar(){
        return car;
    }

    public Car getSample(){
        CarBuilder builder = new CarBuilder();
        builder.setAssembleDate(Timestamp.valueOf(LocalDateTime.now()));
        builder.setColor(new Color(1, "Black"));
        builder.setEngine(new Engine(1,"V8"));
        builder.setId(10);
        builder.setMark(new Mark(1,"Toyota"));
        builder.setModel(new Model(1,new Mark(1,"Toyota"),"Tundra"));
        builder.setPerson(new Person(1,"Yuri","yuri"));
        builder.setSeats(5);
        return builder.getCar();
    }
}
