package ru.inno.services;

import org.springframework.stereotype.Service;
import ru.inno.Security;
import ru.inno.dao.*;
import ru.inno.entity.Car;
import ru.inno.entity.Order;
import ru.inno.entity.Person;

import java.util.List;

@Service
public class CabinetService {

    private Person currentPerson;
    private Security security;
    private CarDAO carDAO;
    private OrderDAO orderDAO;


    public CabinetService(Security security, CarDAO carDAO, OrderDAO orderDAO) {
        this.security = security;
        this.carDAO = carDAO;
        this.orderDAO = orderDAO;
    }

    public Person getCurrentPerson(){
        if (null == currentPerson){
            currentPerson = security.getCurrentUser();
        }
        return currentPerson;
    }

    public List<Car> getCarList(Person person){
//        Connection connection = ConnectionManager.getConnection();
//        CarDAO carDAO = new CarImpl(connection);
        List<Car> carList = carDAO.getCarsByPerson(person.getId());
//        ConnectionManager.closeConnection(connection);
        return carList;
    }



    public List<Order> getCustomerOrders(Person person){
//        Connection connection = ConnectionManager.getConnection();
//        OrderDAO orderDAO = new OrderImpl(connection);
        List<Order> list = orderDAO.getOrdersByCustomer(person.getId());
//        ConnectionManager.closeConnection(connection);
        return list;
    }

}
