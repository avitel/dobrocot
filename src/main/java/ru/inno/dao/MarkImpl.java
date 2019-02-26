package ru.inno.dao;

import ru.inno.entity.Mark;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MarkImpl implements MarkDAO {




    private static final Logger LOGGER = LoggerFactory.getLogger(MarkImpl.class);

    public static final String INSERT_MARK_SQL_TEMPLATE =
            "insert into mark (name) values (?) returning id";

    public static final String GET_MARK_SQL_TEMPLATE =
            "select name from mark where id = ?";

    public static final String GET_MARKS_SQL_TEMPLATE =
            "select id, name from mark";


    private final Connection connection;

    /**
     * Получает подключение и сохраняет в данном объекте.
     * @param connection
     */
    public MarkImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Mark> getMarks() {
        List<Mark> marks = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs;
            rs = statement.executeQuery(GET_MARKS_SQL_TEMPLATE);
            while (rs.next()){
                        marks.add(new Mark(rs.getInt(1),rs.getString(2)));
            }
        }catch (Exception ex){
            LOGGER.debug(ex.getMessage());
            LOGGER.error("Ошибка при получении списка марок!");
        }
        return marks;
    }

    @Override
    public Mark getMark(int id) {
                Mark mark = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_MARK_SQL_TEMPLATE)) {
            statement.setInt(1,id);
            String i = null;
            ResultSet rs;
            rs = statement.executeQuery();
            rs.next();
            i = rs.getString(1);
            if (i==null) {
                throw new SQLException();
            }
            mark = new Mark(id, i);
        }catch (Exception ex){
            LOGGER.debug(ex.getMessage());
            LOGGER.error("Ошибка при получении марки!");
        }
        return mark;
    }

    @Override
    public void addMark(String name) {
        int i = -1;
        try (PreparedStatement statement = connection.prepareStatement(INSERT_MARK_SQL_TEMPLATE)) {
            statement.setString(1, name);
            ResultSet rs;
            rs = statement.executeQuery();
            rs.next();
            i = rs.getInt(1);
            if (i<0) {
                throw new SQLException();
            }
            LOGGER.info("Марка " + name + " успешно добавлена.");
        }catch (Exception ex){
            LOGGER.debug(ex.getMessage());
            LOGGER.error("Ошибка при создании марки!");
        }
    }


}
