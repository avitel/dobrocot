package ru.inno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inno.Security;
import ru.inno.dao.*;
import ru.inno.entity.Car;
import ru.inno.entity.Order;
import ru.inno.entity.Person;

import java.util.List;

@Service
public class CabinetService {

    private Security security;
    private CarDAO carDAO;
    private OrderDAO orderDAO;

    @Autowired
    public CabinetService(Security security, CarDAO carDAO, OrderDAO orderDAO) {
        this.security = security;
        this.carDAO = carDAO;
        this.orderDAO = orderDAO;
    }

    public Person getCurrentPerson() {
        return security.getCurrentUser();
    }

    public List<Car> getCarList(Person person) {
        return carDAO.getCarsByPerson(person.getId());
    }

    public List<Order> getCustomerOrders(Person person) {
        return orderDAO.getOrdersByCustomer(person.getId());
    }

    public List<Order> getSellerOrders(Person person) {
        return orderDAO.getOrdersBySeller(person.getId());
    }

    public boolean deleteCar(int id){
        return carDAO.deleteCar(id);
    }
}
