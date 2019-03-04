package ru.inno.dao;

import ru.inno.entity.Order;
import java.sql.Timestamp;
import java.util.List;

public interface OrderDAO {
    List<Order> getOrders();
    Order getOrder(int id);
    /**
     *
     * Добавляем заказ в базу
     */
    int addOrder(int id_car, int id_owner, int id_customer, Timestamp dateOrder, Timestamp date_begin, Timestamp date_end, int price);

    List<Order> getOrdersByCustomer(int person_id);

    List<Order> getOrdersByCar(int car_id);

    List<Order> getOrdersByCarPresent(int car_id);

    List<Order> getOrdersBySeller(int person_id);
}
