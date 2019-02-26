package ru.inno.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.entity.Engine;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EngineImpl implements EngineDAO{

        private static final Logger LOGGER = LoggerFactory.getLogger(EngineImpl.class);

    public static final String INSERT_ENGINE_SQL_TEMPLATE =
            "insert into engine (name) values (?) returning id";

    public static final String GET_ENGINE_SQL_TEMPLATE =
            "select name from engine where id = ?";

    public static final String GET_ENGINES_SQL_TEMPLATE =
            "select id, name from engine";


    private final Connection connection;

    /**
     * Получает подключение и сохраняет в данном объекте.
     * @param connection
     */
    public EngineImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Engine> getEngines() {

        List<Engine> engines = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs;
            rs = statement.executeQuery(GET_ENGINES_SQL_TEMPLATE);
            while (rs.next()){
                engines.add(new Engine(rs.getInt(1),rs.getString(2)));
            }
            if(engines.size() == 0){
                LOGGER.info("Список двигателей пуст!");
            }
        }catch (Exception ex){
            LOGGER.debug(ex.getMessage());
            LOGGER.error("Ошибка при получении списка двигателей!");
//            System.out.println(ex.getMessage());
//            System.out.println("Ошибка при получении списка двигателей!");
        }
        return engines;
    }

    @Override
    public Engine getEngine(int id) {

                Engine engine = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_ENGINE_SQL_TEMPLATE)) {
            statement.setInt(1,id);
            String i = null;
            ResultSet rs;
            rs = statement.executeQuery();
            rs.next();
            i = rs.getString(1);
            if (i==null) {
                throw new SQLException();
            }
            engine = new Engine(id, i);
        }catch (Exception ex){
            LOGGER.debug(ex.getMessage());
            LOGGER.error("Ошибка при получении двигателя!");
//            System.out.println(ex.getMessage());
//            System.out.println(("Ошибка при получении двигателя!"));
        }
        return engine;
    }

    @Override
    public void addEngine(String name) {
        int i = -1;
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ENGINE_SQL_TEMPLATE)) {
            statement.setString(1, name);
            ResultSet rs;
            rs = statement.executeQuery();
            rs.next();
            i = rs.getInt(1);
            if (i<0) {
                throw new SQLException();
            }
            LOGGER.info("Двигатель " + name + " успешно добавлен.");
//            System.out.println("Двигатель " + name + " успешно добавлен.");
        }catch (Exception ex){
            LOGGER.debug(ex.getMessage());
//            System.out.println(ex.getMessage());
            LOGGER.error("Ошибка при создании двигателя!");
//            System.out.println("Ошибка при создании двигателя!");
        }
    }
}
