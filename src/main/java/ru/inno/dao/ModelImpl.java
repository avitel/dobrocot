package ru.inno.dao;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.entity.Mark;
import ru.inno.entity.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ModelImpl implements ModelDAO {


    private static final Logger LOGGER = LoggerFactory.getLogger(ModelImpl.class);

    public static final String INSERT_MODEL_SQL_TEMPLATE =
            "insert into model (id_mark, name) values (?, ?) returning id";

        public static final String GET_MODEL_SQL_TEMPLATE =
            "select id_mark, ma.name, mo.name from model mo, mark ma where mo.id_mark = ma.id and mo.id = ?";

        public static final String GET_MODELS_SQL_TEMPLATE =
            "select mo.id, id_mark, ma.name, mo.name from model mo, mark ma where mo.id_mark = ma.id";


    private final Connection connection;

    /**
     * Получает подключение и сохраняет в данном объекте.
     * @param connection
     */
    public ModelImpl(Connection connection) {
        this.connection = connection;
    }




    @Override
    public Collection<Model> getModels() {
        Collection<Model> models = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs;
            rs = statement.executeQuery(GET_MODELS_SQL_TEMPLATE);
            while (rs.next()){
                models.add(new Model(rs.getInt(1), new Mark(rs.getInt(2),rs.getString(3)) , rs.getString(4)));
            }
        }catch (Exception ex){
//            LOGGER.debug(ex.getMessage();
//            LOGGER.error("Ошибка при получении списка студентов!");
            System.out.println(ex.getMessage());
            System.out.println("Ошибка при получении списка студентов!");
        }
        return models;
    }

    @Override
    public Model getModel(int id) {
        Model model = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_MODEL_SQL_TEMPLATE)) {
            statement.setInt(1,id);
            int i = -1;
            ResultSet rs;
            rs = statement.executeQuery();
            rs.next();
            i = rs.getInt(1);
            if (i<0) {
                throw new SQLException();
            }
            //mo.id, id_mark, ma.name, mo.name
            model = new Model(id, new Mark(rs.getInt(1),rs.getString(2)) , rs.getString(3));
        }catch (Exception ex){
            LOGGER.debug(ex.getMessage());
            LOGGER.error("Ошибка при получении модели!");
        }
        return model;
    }

    @Override
    public void addModel(int id_mark, String name) {
        int i = -1;
        try (PreparedStatement statement = connection.prepareStatement(INSERT_MODEL_SQL_TEMPLATE)) {
            statement.setInt(1, id_mark);
            statement.setString(2, name);
            ResultSet rs;
            rs = statement.executeQuery();
            rs.next();
            i = rs.getInt(1);
            if (i<0) {
                throw new SQLException();
            }
            connection.commit();
            LOGGER.info("Модель " + name + " успешно добавлена.");
        }catch (Exception ex){
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            LOGGER.debug(ex.getMessage());
            LOGGER.error("Ошибка при создании модели!");
        }
    }
}
