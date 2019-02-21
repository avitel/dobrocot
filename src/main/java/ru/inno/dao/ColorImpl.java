package ru.inno.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.entity.Color;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ColorImpl implements ColorDAO {


    private static final Logger LOGGER = LoggerFactory.getLogger(ColorImpl.class);

    public static final String INSERT_COLOR_SQL_TEMPLATE =
            "insert into color (name) values (?) returning id";

    public static final String GET_COLOR_SQL_TEMPLATE =
            "select name from color where id = ?";

    public static final String GET_COLORS_SQL_TEMPLATE =
            "select id, name from color";


    private final Connection connection;

    /**
     * Получает подключение и сохраняет в данном объекте.
     * @param connection
     */
    public ColorImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Collection<Color> getColors() {

        Collection<Color> colors = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs;
            rs = statement.executeQuery(GET_COLORS_SQL_TEMPLATE);
            while (rs.next()){
                colors.add(new Color(rs.getInt(1),rs.getString(2)));
            }
            if(colors.size() == 0){
                LOGGER.info("Список цветов пуст!");
            }
        }catch (Exception ex){
            LOGGER.debug(ex.getMessage());
            LOGGER.error("Ошибка при получении списка цветов!");
        }
        return colors;
    }

    @Override
    public Color getColor(int id) {
        Color color = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_COLOR_SQL_TEMPLATE)) {
            statement.setInt(1,id);
            String i = null;
            ResultSet rs;
            rs = statement.executeQuery();
            rs.next();
            i = rs.getString(1);
            if (i==null) {
                throw new SQLException();
            }
            color = new Color(id, i);
        }catch (Exception ex){
            LOGGER.debug(ex.getMessage());
            LOGGER.error("Ошибка при получении цвета!");
        }
        return color;
    }

    @Override
    public void addColor(String name) {
        int i = -1;
        try (PreparedStatement statement = connection.prepareStatement(INSERT_COLOR_SQL_TEMPLATE)) {
            statement.setString(1, name);
            ResultSet rs;
            rs = statement.executeQuery();
            rs.next();
            i = rs.getInt(1);
            if (i<0) {
                throw new SQLException();
            }
            LOGGER.info("Цвет " + name + " успешно добавлен.");
        }catch (Exception ex){
            LOGGER.debug(ex.getMessage());
            LOGGER.error("Ошибка при создании цвета!");
        }
    }
}
