package ru.inno.dao;

import ru.inno.entity.Car;

import java.sql.Timestamp;
import java.util.Collection;

public class CarImpl implements CarDAO {
    private static final String ADD_CAR_SQL_TEMPLATE =
            "insert into car (owner_id, mark_id, model_id, assembledate, engine_id, numberofseats, color_id) \n" +
                    "values (?,?,?,?,?,?,?) ";
    public void addCar() {

    }

    @Override
    public Collection<Car> getCars() {
        return null;
    }

    @Override
    public Car getCar(int id) {
        return null;
    }

    @Override
    public void addCar(int owner_id, int mark_id, int model_id, Timestamp assembledate, int engine_id, int numbeerofseats) {

    }
}
