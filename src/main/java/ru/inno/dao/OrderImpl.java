package ru.inno.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.entity.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderImpl implements OrderDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderImpl.class);

    public static final String INSERT_ORDER_SQL_TEMPLATE =
            "insert into _order (car, seller, customer, date, begindate, enddate, price) values (?,?,?,?,?,?,?)";

    public static final String GET_ORDERS_BY_CUSTOMER_SQL_TEMPLATE =
            "select * from _order where customer = ?";

    public static final String GET_ORDERS_BY_CAR_SQL_TEMPLATE =
            "select * from _order where car = ?";

    private final Connection connection;
    private CarDAO cardao;
    private PersonDAO persondao;

    public OrderImpl(Connection connection, CarDAO cardao, PersonDAO persondao) {
        this.connection = connection;
        this.cardao = cardao;
        this.persondao = persondao;
    }

    public OrderImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void addOrder(int id_car, int id_owner, int id_customer, Timestamp dateOrder, Timestamp date_begin, Timestamp date_end, int price) {
        int i = -1;
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ORDER_SQL_TEMPLATE)) {
            statement.setInt(1, id_car);
            statement.setInt(2, id_owner);
            statement.setInt(3, id_customer);
            statement.setTimestamp(4, dateOrder);
            statement.setTimestamp(5, date_begin);
            statement.setTimestamp(6, date_end);
            statement.setInt(7, price);
            statement.execute();
            LOGGER.info("Сделка " + i + " успешно добавлена.");
        } catch (Exception ex) {
            LOGGER.debug(ex.getMessage());
            LOGGER.error("Ошибка при создании сделки!");

        }
    }

    @Override
    public List<Order> getOrdersByCustomer(int person_id) {
        return getOrderList(GET_ORDERS_BY_CUSTOMER_SQL_TEMPLATE, person_id);
    }


    @Override
    public List<Order> getOrdersBySeller(int person_id) {
        return getOrderList(GET_ORDERS_BY_SELLER_SQL_TEMPLATE, person_id);
    }


    private List<Order> getOrderList(String query, int personId) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, personId);
            ResultSet rs = statement.executeQuery();

            List<Order> list = new ArrayList<>();
            while (rs.next()) {


                Car car = cardao.getCar(rs.getInt("car"));
                Person seller = persondao.getPerson(rs.getInt("seller"));
                Person customer = persondao.getPerson(rs.getInt("customer"));
                list.add(new Order(
                        rs.getInt("id"), car, seller, customer,
                        rs.getTimestamp("date"),
                        rs.getTimestamp("begindate"),
                        rs.getTimestamp("enddate"),
                        rs.getInt("price")));

                LOGGER.debug("{} {}", query, personId);
                LOGGER.debug("car({}) seller({}) customer({})", car, seller, customer);
            }


            return list;
        } catch (SQLException ex) {
            LOGGER.debug(ex.getMessage());
            LOGGER.error("get car query error");
        }

        return null;
    }

    @Override
    public List<Order> getOrdersByCar(int car_id) {
        try (PreparedStatement statement = connection.prepareStatement(GET_ORDERS_BY_CAR_SQL_TEMPLATE)) {
            statement.setInt(1, car_id);
            ResultSet rs = statement.executeQuery();

            List<Order> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Order(rs.getInt("id"),
                        rs.getTimestamp("begindate"),
                        rs.getTimestamp("enddate")));

            }
            return list;

        } catch (SQLException ex) {
            LOGGER.debug("get car query error", ex);
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
