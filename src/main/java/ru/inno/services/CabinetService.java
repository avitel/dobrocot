package ru.inno.services;

import org.springframework.beans.factory.annotation.Autowired;
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

    private Person currentPerson;
    private Security security;

    @Autowired
    public void setSecurity(Security security) {
        this.security = security;
    }

    public Person getCurrentPerson(){
        if (null == currentPerson){
            currentPerson = security.getCurrentUser();
        }
        return currentPerson;
    }

    public List<Car> getCarList(Person person){
        Connection connection = ConnectionManager.getConnection();
        CarDAO carDAO = new CarImpl(connection);
        List<Car> carList = carDAO.getCarsByPerson(person.getId());
        ConnectionManager.closeConnection(connection);
        return carList;
    }



    public List<Order> getCustomerOrders(Person person){
        Connection connection = ConnectionManager.getConnection();
        OrderDAO orderDAO = new OrderImpl(connection);
        List<Order> list = orderDAO.getOrdersByCustomer(person.getId());
        ConnectionManager.closeConnection(connection);
        return list;
    }

}
