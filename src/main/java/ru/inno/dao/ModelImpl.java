package ru.inno.dao;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.entity.Mark;
import ru.inno.entity.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements ModelDAO {


    private static final Logger LOGGER = LoggerFactory.getLogger(ModelImpl.class);

    public static final String INSERT_MODEL_SQL_TEMPLATE =
            "insert into model (id_mark, name) values (?, ?) returning id";

    public static final String GET_MODEL_SQL_TEMPLATE =
            "select id_mark, ma.name, mo.name from model mo, mark ma where mo.id_mark = ma.id and mo.id = ?";

    public static final String GET_MODELS_BY_MARK_SQL_TEMPLATE =
            "select mo.id, id_mark, ma.name, mo.name " +
                    "from model mo " +
                    "left join mark ma " +
                    "on mo.id_mark = ma.id ";


    private final Connection connection;

    /**
     * Получает подключение и сохраняет в данном объекте.
     * @param connection
     */
    public ModelImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Model> getModels(Integer mark_id) {

        StringBuilder query = new StringBuilder(GET_MODELS_BY_MARK_SQL_TEMPLATE);
        if (mark_id != null){
            query.append(" where ma.id=?");
        }

        List<Model> models = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query.toString())) {
            if (mark_id != null){
                statement.setInt(1,mark_id);
            }

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                models.add(new Model(rs.getInt(1),
                        new Mark(rs.getInt(2), rs.getString(3)) ,
                        rs.getString(4)));
            }
        }catch (Exception ex){
            LOGGER.debug(ex.getMessage());
            LOGGER.error("Ошибка при получении списка моделей!");
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
            LOGGER.info("Модель " + name + " успешно добавлена.");
        }catch (Exception ex){
            LOGGER.debug(ex.getMessage());
            LOGGER.error("Ошибка при создании модели!");
        }
    }
}
