package ru.inno.dao;

import ru.inno.entity.Car;

import java.sql.Timestamp;
import java.util.List;

public interface CarDAO {

    Car getCar(int id);

    int addCar(int owner_id, int mark_id, int model_id, Timestamp assembledate, int engine_id, int numbeerofseats, int color_id, int dayprice);

    List<Car> getFilteredCars(QueryBuilder filter);

    List<Car> getCarsByPerson(int person_id);

    boolean deleteCar(int id);
}
