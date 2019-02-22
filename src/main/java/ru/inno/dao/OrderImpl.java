package ru.inno.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.entity.Engine;
import ru.inno.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class OrderImpl implements OrderDAO{

        private static final Logger LOGGER = LoggerFactory.getLogger(OrderImpl.class);
//int id_car, int id_owner, int id_customer, Timestamp dateOrder
    public static final String INSERT_ORDER_SQL_TEMPLATE =
            "insert into \"order\" (car, seller, customer, date) values (?,?,?,?)";

//    public static final String GET_ORDER_SQL_TEMPLATE =
//            "select name from engine where id = ?";
//
//    public static final String GET_ORDER_SQL_TEMPLATE =
//            "select id, name from engine";


    private final Connection connection;

    /**
     * Получает подключение и сохраняет в данном объекте.
     * @param connection
     */
    public OrderImpl(Connection connection) {
        this.connection = connection;
    }

//    @Override
//    public Collection<Engine> getEngines() {
//
//        Collection<Engine> engines = new ArrayList<>();
//        try (Statement statement = connection.createStatement()) {
//            ResultSet rs;
//            rs = statement.executeQuery(GET_ENGINES_SQL_TEMPLATE);
//            while (rs.next()){
//                engines.add(new Engine(rs.getInt(1),rs.getString(2)));
//            }
//            if(engines.size() == 0){
//                LOGGER.info("Список двигателей пуст!");
//            }
//        }catch (Exception ex){
//            LOGGER.debug(ex.getMessage());
//            LOGGER.error("Ошибка при получении списка двигателей!");
////            System.out.println(ex.getMessage());
////            System.out.println("Ошибка при получении списка двигателей!");
//        }
//        return engines;
//    }
//
//    @Override
//    public Engine getEngine(int id) {
//
//                Engine engine = null;
//        try (PreparedStatement statement = connection.prepareStatement(GET_ENGINE_SQL_TEMPLATE)) {
//            statement.setInt(1,id);
//            String i = null;
//            ResultSet rs;
//            rs = statement.executeQuery();
//            rs.next();
//            i = rs.getString(1);
//            if (i==null) {
//                throw new SQLException();
//            }
//            engine = new Engine(id, i);
//        }catch (Exception ex){
//            LOGGER.debug(ex.getMessage());
//            LOGGER.error("Ошибка при получении двигателя!");
////            System.out.println(ex.getMessage());
////            System.out.println(("Ошибка при получении двигателя!"));
//        }
//        return engine;
//    }

    @Override
    public void addOrder(int id_car, int id_owner, int id_customer, Timestamp dateOrder) {
        int i = -1;
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ORDER_SQL_TEMPLATE)) {
            statement.setInt(1, id_car);
            statement.setInt(2, id_owner);
            statement.setInt(3, id_customer);
//            statement.setString(4, dateOrder.toString());
            statement.setTimestamp(4,dateOrder);
            statement.execute();
            LOGGER.info("Сделка " + i + " успешно добавлена.");
        }catch (Exception ex){
            LOGGER.debug(ex.getMessage());
            LOGGER.error("Ошибка при создании сделки!");

        }
    }

    @Override
    public Collection<Order> getOrders() {
        return null;
    }

    @Override
    public Order getOrder(int id) {
        return null;
    }

}
