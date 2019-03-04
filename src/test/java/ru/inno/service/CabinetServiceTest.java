package ru.inno.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.inno.Security;
import ru.inno.dao.CarDAO;
import ru.inno.dao.OrderDAO;
import ru.inno.entity.Car;
import ru.inno.entity.Order;
import ru.inno.entity.Person;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CabinetServiceTest {


    private Security security;
    private CarDAO carDAO;
    private OrderDAO orderDAO;
    private CabinetService cabinetService;
    private Person person;
    private List<Car> cars;
    private List<Order> orders;

    @BeforeEach
    void setUp() {
        security = mock(Security.class);
        carDAO = mock(CarDAO.class);
        orderDAO = mock(OrderDAO.class);
        cabinetService = new CabinetService(security, carDAO, orderDAO);
        person = new Person();
        cars = Collections.singletonList(new Car());
        orders = Collections.singletonList(new Order());
    }

    @Test
    void getCurrentPerson() {
        when(security.getCurrentUser()).thenReturn(person);
        assertEquals(person, cabinetService.getCurrentPerson());
    }

    @Test
    void getCarList() {
        when(cabinetService.getCarList(person)).thenReturn(cars);
        assertEquals(cars, cabinetService.getCarList(person));
    }

    @Test
    void getCustomerOrders() {
        when(cabinetService.getCustomerOrders(person)).thenReturn(orders);
        assertEquals(orders, cabinetService.getCustomerOrders(person));
    }

    @Test
    void getSellerOrders() {
        when(cabinetService.getSellerOrders(person)).thenReturn(orders);
        assertEquals(orders, cabinetService.getSellerOrders(person));
    }
}