package ru.inno.dao;

import ru.inno.CarFilter;
import ru.inno.entity.Car;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public interface CarDAO {

    Collection<Car> getCars();

    Car getCar(int id);

    /**
     * Добавляет car в базу
     */
    void addCar(int owner_id, int mark_id, int model_id, Timestamp assembledate, int engine_id, int numbeerofseats);

    List<Car> getFilteredCars(CarFilter filter);

}
