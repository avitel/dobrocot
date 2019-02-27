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
    private CarDAO carDAO;
    private OrderDAO orderDAO;

    @Autowired
    public CabinetService(Security security, CarDAO carDAO, OrderDAO orderDAO) {
        this.security = security;
        this.carDAO = carDAO;
        this.orderDAO = orderDAO;
    }

    public Person getCurrentPerson(){
        currentPerson = security.getCurrentUser();
        return currentPerson;
    }

    public List<Car> getCarList(Person person){
        List<Car> carList = carDAO.getCarsByPerson(person.getId());
        return carList;
    }

    public List<Order> getCustomerOrders(Person person){
        List<Order> list = orderDAO.getOrdersByCustomer(person.getId());
        return list;
    }

}
