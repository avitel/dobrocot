package ru.inno.dao;

import ru.inno.entity.Engine;
import ru.inno.entity.Order;

import java.sql.Timestamp;
import java.util.Collection;

public interface OrderDAO {
    Collection<Order> getOrders();
    Order getOrder(int id);
    /**
     *
     * Добавляем заказ в базу
     */
    void addOrder(int id_car, int id_owner, int id_customer, Timestamp dateOrder);
}
