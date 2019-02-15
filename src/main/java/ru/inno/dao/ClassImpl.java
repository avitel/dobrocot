package ru.inno.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.entity.Class;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ClassImpl implements ClassDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassImpl.class);

    public static final String INSERT_CLASS_SQL_TEMPLATE =
            "insert into class (name) values (?) returning id";

    public static final String GET_CLASS_SQL_TEMPLATE =
            "select name from class where id = ?";

    public static final String GET_CLASSES_SQL_TEMPLATE =
            "select id, name from class";


    private final Connection connection;

    /**
     * Получает подключение и сохраняет в данном объекте.
     * @param connection
     */
    public ClassImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Collection<Class> getClasses() {
        Collection<Class> aClasses = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs;
            rs = statement.executeQuery(GET_CLASSES_SQL_TEMPLATE);
            while (rs.next()){
                aClasses.add(new Class(rs.getInt(1),rs.getString(2)));
            }
            if(aClasses.size() == 0){
                LOGGER.info("Список классов авто пуст!");
            }
        }catch (Exception ex){
            LOGGER.debug(ex.getMessage());
            LOGGER.error("Ошибка при получении списка классов авто!");
        }
        return aClasses;
    }

    @Override
    public Class getClass(int id) {
        Class aClass = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_CLASS_SQL_TEMPLATE)) {
            statement.setInt(1,id);
            String i = null;
            ResultSet rs;
            rs = statement.executeQuery();
            rs.next();
            i = rs.getString(1);
            if (i==null) {
                throw new SQLException();
            }
            aClass = new Class(id, i);
        }catch (Exception ex){
            LOGGER.debug(ex.getMessage());
            LOGGER.error("Ошибка при получении класса автомобиля!");
        }
        return aClass;
    }

    @Override
    public void addClass(String name) {
        int i = -1;
        try (PreparedStatement statement = connection.prepareStatement(INSERT_CLASS_SQL_TEMPLATE)) {
            statement.setString(1, name);
            ResultSet rs;
            rs = statement.executeQuery();
            rs.next();
            i = rs.getInt(1);
            if (i<0) {
                throw new SQLException();
            }
            connection.commit();
            LOGGER.info("Класс автомобиля " + name + " успешно добавлен.");
        }catch (Exception ex){
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            LOGGER.debug(ex.getMessage());
            LOGGER.error("Ошибка при создании класса автомобиля!");
        }
    }
}
