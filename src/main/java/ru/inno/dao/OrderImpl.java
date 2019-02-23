package ru.inno.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.ConnectionManager;
import ru.inno.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OrderImpl implements OrderDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderImpl.class);

    public static final String INSERT_ORDER_SQL_TEMPLATE =
            "insert into \"order\" (car, seller, customer, date) values (?,?,?,?)";

    public static final String GET_ORDERS_BY_CUSTOMER_SQL_TEMPLATE =
            "select * from \"order\" where customer = ?";



    private final Connection connection;


    public OrderImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void addOrder(int id_car, int id_owner, int id_customer, Timestamp dateOrder) {
        int i = -1;
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ORDER_SQL_TEMPLATE)) {
            statement.setInt(1, id_car);
            statement.setInt(2, id_owner);
            statement.setInt(3, id_customer);
            statement.setTimestamp(4,dateOrder);
            statement.execute();
            LOGGER.info("Сделка " + i + " успешно добавлена.");
        }catch (Exception ex){
            LOGGER.debug(ex.getMessage());
            LOGGER.error("Ошибка при создании сделки!");

        }
    }

    @Override
    public List<Order> getOrdersByCustomer(int person_id) {

        try (PreparedStatement statement = connection.prepareStatement(GET_ORDERS_BY_CUSTOMER_SQL_TEMPLATE)) {
            statement.setInt(1, person_id);
            ResultSet rs = statement.executeQuery();

            List<Order> list = new ArrayList<>();
            while (rs.next()) {

                Connection c = ConnectionManager.getConnection();
                CarDAO cardao = new CarImpl(c);
                PersonDAO persondao = new PersonImpl(c);

                Car car = cardao.getCar(rs.getInt("car"));
                Person seller = persondao.getPerson(rs.getInt("seller"));
                Person customer = persondao.getPerson(rs.getInt("customer"));

                list.add( new Order(rs.getInt("id"),
                        car,
                        customer,
                        seller,
                        rs.getTimestamp("date")));

            }
            return list;

        } catch (Exception ex) {
            LOGGER.debug(ex.getMessage());
            LOGGER.error("get car query error");
        }

        return null;
    }

    @Override
    public List<Order> getOrders() {
        return null;
    }

    @Override
    public Order getOrder(int id) {
        return null;
    }

}
