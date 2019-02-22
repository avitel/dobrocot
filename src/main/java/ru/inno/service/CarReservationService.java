package ru.inno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inno.ConnectionManager;
import ru.inno.dao.CarDAO;
import ru.inno.dao.CarImpl;
import ru.inno.entity.Car;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CarReservationService implements ReservableService {

    @Override
    public Map<String,String> getValues(String ... requestParams) {
        System.out.println("теперь тут>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        int id = Integer.parseInt(requestParams[0]);
        Connection c = ConnectionManager.getConnection();
        CarDAO carDAO = new CarImpl(c);
        Car car = carDAO.getCar(id);
        HashMap<String,String> carParamsMap = new HashMap<>();
        carParamsMap.put("color",car.getColor().getName());
        carParamsMap.put("engine",car.getEngine().getName());
        carParamsMap.put("mark",car.getMark().getName());
        carParamsMap.put("model",car.getModel().getName());
        carParamsMap.put("numberofseats",String.valueOf(car.getNumberofseats()));
        carParamsMap.put("owner",car.getOwner().getName());
        ConnectionManager.closeConnection(c);
        return carParamsMap;
    }
}
