package ru.inno.services;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.inno.ConnectionManager;
import ru.inno.Security;
import ru.inno.dao.*;
import ru.inno.entity.Car;
import ru.inno.entity.Order;
import ru.inno.entity.Person;

import java.sql.Connection;
import java.util.List;

@Service
public class CabinetService {

    public List<Car> getCarList(){
        Security security = new Security(SecurityContextHolder.getContext(), new PersonImpl(ConnectionManager.getConnection()));
        Person user = security.getCurrentUser();
        Connection c = ConnectionManager.getConnection();
        CarDAO carDAO = new CarImpl(c);
        List<Car> carList = carDAO.getCarsByPerson(user.getId());

        ConnectionManager.closeConnection(c);
        return null;
    }

    public List<Order> getOrderList(){
        return null;
    }

}
