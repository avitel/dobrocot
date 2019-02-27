package ru.inno.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.entity.*;

import java.sql.*;
import java.util.*;

public class CarImpl implements CarDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarImpl.class);


    private static final String ADD_CAR_SQL_TEMPLATE =
            "insert into car (owner_id, mark_id, model_id, assembledate, engine_id, numberofseats, color_id) \n" +
                    "values (?,?,?,?,?,?,?) ";

    public static final String GET_FILTERED_CARS_SQL_TEMPLATE =
            "select * from car where ";

    public static final String GET_CAR_SQL_TEMPLATE =
            QueryBuilder.GET_FILTERED_CARS_SQL_TEMPLATE + "\n where car.id = ?";


    private final Connection connection;


    public CarImpl(Connection connection) {

        this.connection = connection;
    }


    @Override
    public Car getCar(int id) {

        try (PreparedStatement statement = connection.prepareStatement(GET_CAR_SQL_TEMPLATE)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return getCarObject(rs);
            }

        } catch (Exception ex) {
            LOGGER.debug(ex.getMessage());
            LOGGER.error("get car query error");
        }
        return null;
    }


    @Override
    public int addCar(int owner_id, int mark_id, int model_id, Timestamp assembledate, int engine_id, int color_id, int numbeerofseats, int dayprice) {
        return 0;
    }


    @Override
    public List<Car> getFilteredCars(QueryBuilder filter) {

        try (PreparedStatement statement = connection.prepareStatement(filter.getSQLquery())) {

            ResultSet rs = statement.executeQuery();

            ArrayList<Car> ls = new ArrayList<>();

            while (rs.next()) {
                ls.add(getCarObject(rs));
            }

            return ls;

        } catch (Exception ex) {
            LOGGER.error("", ex);
        }

        return null;
    }


    @Override
    public List<Car> getCarsByPerson(int person_id) {
        QueryBuilder builder = new QueryBuilder(null, null, null, null, person_id);
        return getFilteredCars(builder);
    }


    private Car getCarObject(ResultSet rs) throws SQLException {

        Person person = new Person(rs.getInt("owner_id"), rs.getString("person_name"), rs.getString("person_login"));
        Mark mark = new Mark(rs.getInt("mark_id"), rs.getString("mark_name"));
        Model model = new Model(rs.getInt("model_id"), mark, rs.getString("model_name"));
        Engine engine = new Engine(rs.getInt("engine_id"), rs.getString("engine_name"));
        Color color = new Color(rs.getInt("color_id"), rs.getString("color_name"));

        return new Car(
                rs.getInt("id"),
                person,
                mark,
                model,
                rs.getTimestamp("assembledate"),
                engine,
                rs.getInt("numberofseats"),
                color
                , rs.getInt("dayprice")
        );
    }
}
