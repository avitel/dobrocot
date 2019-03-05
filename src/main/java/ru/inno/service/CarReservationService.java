package ru.inno.service;

import org.springframework.stereotype.Service;
import ru.inno.Security;
import ru.inno.dao.*;
import ru.inno.entity.Car;
import ru.inno.entity.Order;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CarReservationService implements ReservableService {

    private Security security;
    private OrderDAO order;
    private CarDAO carDAO;

    public CarReservationService(Security security, OrderDAO order, CarDAO carDAO) {
        this.security = security;
        this.order = order;
        this.carDAO = carDAO;
    }

    @Override
    public Map<String, String> getValues(String... requestParams) {
        int id = Integer.parseInt(requestParams[0]);
        Car car = carDAO.getCar(id);
        HashMap<String, String> carParamsMap = new HashMap<>();
        carParamsMap.put("color", car.getColor().getName());
        carParamsMap.put("engine", car.getEngine().getName());
        carParamsMap.put("mark", car.getMark().getName());
        carParamsMap.put("model", car.getModel().getName());
        carParamsMap.put("numberofseats", String.valueOf(car.getNumberofseats()));
        carParamsMap.put("owner", car.getOwner().getName());
        carParamsMap.put("id_owner", String.valueOf(car.getOwner().getId()));
        carParamsMap.put("assembledate", String.valueOf(car.getAssembledate().toLocalDateTime().getYear()));
        carParamsMap.put("dayprice", String.valueOf(car.getDayprice()));
        return carParamsMap;
    }

    @Override
    public Map getReservedDates(String car_id) {
        int id = Integer.parseInt(car_id);
        Map<String, String> map = new HashMap<>();
        //List<Order> list = order.getOrdersByCar(id);
        List<Order> list = order.getOrdersByCarPresent(id);
        for (Order ord : list) {
            map.put(ord.getBegindate().toLocalDateTime().format(DateTimeFormatter.ISO_DATE),
                    ord.getEnddate().toLocalDateTime().format(DateTimeFormatter.ISO_DATE));
        }
        return map;
    }

    @Override
    public int getDays(Date d1, Date d2) throws Exception {
        int days = (int) ((d2.getTime() - d1.getTime()) / 86400000);
        if (days <= 0) {
            throw new Exception("Неверные даты");
        }
        return days;
    }

    @Override
    public boolean checkAvailableDate(Date d1, Date d2, String car_id) {
        int id = Integer.parseInt(car_id);
        //List<Order> list = order.getOrdersByCar(id);
        List<Order> list = order.getOrdersByCarPresent(id);
        Timestamp ts1 = new Timestamp(d1.getTime());
        Timestamp ts2 = new Timestamp(d2.getTime());
        for (Order ord : list) {
            if (!(
                    (ts2.before(ord.getBegindate()) & ts1.before(ord.getBegindate())
                            | ts1.after(ord.getEnddate()) & ts2.after(ord.getEnddate()))
                            & ts2.after(ts1)
                            & ts1.after(new Timestamp(new GregorianCalendar().getTimeInMillis()-86400000))
            )) {
                return false;
            }
        }
        if(!(ts2.after(ts1) & ts1.after(new Timestamp(new GregorianCalendar().getTimeInMillis()-86400000)))){
            return false;
        }
        return true;
    }

    @Override
    public boolean checkAvailableCustomer(int id_seller) {
        int id_customer = security.getCurrentUser().getId();
        return id_seller!=id_customer;
    }

    @Override
    public void addReservationOrder(int id_car, int id_owner, Date date_begin, Date date_end, int price) {
        int id_customer = security.getCurrentUser().getId();
        order.addOrder(id_car, id_owner, id_customer, new Timestamp(new GregorianCalendar().getTimeInMillis()),
                new Timestamp(date_begin.getTime()),
                new Timestamp(date_end.getTime()),
                price);
    }
}
