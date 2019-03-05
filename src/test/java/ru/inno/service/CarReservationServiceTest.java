package ru.inno.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.inno.Security;
import ru.inno.dao.CarDAO;
import ru.inno.dao.OrderDAO;
import ru.inno.entity.Order;
import ru.inno.entity.Person;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

class CarReservationServiceTest {
    Security security;
    CarDAO carDAO;
    OrderDAO orderDAO;
    CarReservationService reservationService;

    @BeforeEach
    void setUp() {
        orderDAO = Mockito.mock(OrderDAO.class);
        carDAO = Mockito.mock(CarDAO.class);
        security = Mockito.mock(Security.class);
        reservationService = new CarReservationService(security, orderDAO, carDAO);
    }

    @Test
    void getReservedDatesPositive() {
        List<Order> list = new ArrayList<>();
        Order ord = new Order();
        ord.setBegindate(new Timestamp(System.currentTimeMillis()+10_000_000));
        ord.setEnddate(new Timestamp(System.currentTimeMillis()+20_000_000));
        list.add(ord);
        Mockito.when(orderDAO.getOrdersByCarPresent(Mockito.anyInt())).thenReturn(list);
        Map map = reservationService.getReservedDates("1");
        Assertions.assertEquals(map.size(), 1);
    }

    @Test
    void getDays() throws Exception {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = formatter.parse("2019-01-10");
        Date d2 = formatter.parse("2019-01-15");
        Assertions.assertEquals(reservationService.getDays(d1, d2), 5);
    }

    @Test
    void checkAvailableDateNegative() throws Exception {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = formatter.parse("2019-01-10");
        Date d2 = formatter.parse("2019-01-15");

        List<Order> list = new ArrayList<>();
        Order ord = new Order();
        ord.setBegindate(new Timestamp(d1.getTime()));
        ord.setEnddate(new Timestamp(d2.getTime()));
        list.add(ord);
        Mockito.when(orderDAO.getOrdersByCar(Mockito.anyInt())).thenReturn(list);
        Assertions.assertNotEquals(reservationService.checkAvailableDate(formatter.parse("2019-01-08"), formatter.parse("2019-01-12"), "10"), true);
    }

    @Test
    void checkAvailableDatePositive() throws Exception {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = formatter.parse("2019-01-10");
        Date d2 = formatter.parse("2019-01-15");

        List<Order> list = new ArrayList<>();
        Order ord = new Order();
        ord.setBegindate(new Timestamp(d1.getTime()));
        ord.setEnddate(new Timestamp(d2.getTime()));
        list.add(ord);
        Mockito.when(orderDAO.getOrdersByCar(Mockito.anyInt())).thenReturn(list);
        Assertions.assertEquals(reservationService.checkAvailableDate(formatter.parse("2019-05-08"), formatter.parse("2019-05-09"), "10"), true);
    }

    @Test
    public void checkAvailableCustomerPositive() {
        int id_seller = 2;
        Person person = Mockito.mock(Person.class);
        Mockito.when(person.getId()).thenReturn(1);
        Mockito.when(security.getCurrentUser()).thenReturn(person);
        Assertions.assertEquals(reservationService.checkAvailableCustomer(
                id_seller), true);
    }

    @Test
    public void checkAvailableCustomerNegative() {
        int id_seller = 1;
        Person person = Mockito.mock(Person.class);
        Mockito.when(person.getId()).thenReturn(1);
        Mockito.when(security.getCurrentUser()).thenReturn(person);
        Assertions.assertNotEquals(reservationService.checkAvailableCustomer(
                id_seller), true);
    }

    @Test
    void addReservationOrder() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = formatter.parse("2019-01-10");
        Date d2 = formatter.parse("2019-01-15");
        Date d3 = formatter.parse("2019-01-20");
        Person person = Mockito.mock(Person.class);
        Mockito.when(person.getId()).thenReturn(1);
        Mockito.when(security.getCurrentUser()).thenReturn(person);
        reservationService.addReservationOrder(1, 1,
                new Timestamp(d2.getTime()),
                new Timestamp(d3.getTime()),
                1);
        Mockito.verify(orderDAO, Mockito.times(1)).addOrder(
                Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(),
                Mockito.any(),
                Mockito.any(),
                Mockito.any(),
                Mockito.anyInt());
    }
}