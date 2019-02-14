package ru.inno.dao;

import ru.inno.entity.Car;

import java.sql.Timestamp;
import java.util.Collection;

public interface CarDAO {
    Collection<Car> getCars();
    Car getCar(int id);
    /**
     * Добавляет car в базу
     */
    void addCar(int owner_id, int mark_id, int model_id, Timestamp assembledate, int engine_id, int numbeerofseats);
}
