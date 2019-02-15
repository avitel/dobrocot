package ru.inno.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.CarFilter;
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


    //map of fields of Class and sql table fields
    //key = field in object, value = sql field
    private static final HashMap<String, String> sqlKeys = new HashMap<>();


    private final Connection connection;


    public CarImpl(Connection connection) {

        this.connection = connection;

        sqlKeys.put("mark_id","mark_id");
        sqlKeys.put("model_id","model_id");
        sqlKeys.put("stamp assembledate","assembledate");
        sqlKeys.put("engine_id","engine_id");
        sqlKeys.put("numberofseats","numberofseats");
    }



    public String getSQLField(String field){
        return sqlKeys.get(field);
    }


    public String getObjectFiled(String sqlField){
        for (Map.Entry<String, String> entry : sqlKeys.entrySet()) {
            if (entry.getValue() == sqlField) {
                return entry.getKey();
            }
        }
        return null;
    }



    public void addCar() {

    }

    @Override
    public Collection<Car> getCars() {
        return null;
    }

    @Override
    public Car getCar(int id) {
        return null;
    }

    @Override
    public void addCar(int owner_id, int mark_id, int model_id, Timestamp assembledate, int engine_id, int numbeerofseats) {

    }


    @Override
    public List<Car> getFilteredCars(CarFilter filter) {

        try (PreparedStatement statement = connection.prepareStatement(filter.getSQLquery())) {

            ResultSet rs;
            rs = statement.executeQuery();

            ArrayList<Car> ls = new ArrayList<>();

            while (rs.next()) {

                ls.add(new Car(rs.getInt("id"),
                        new Person(rs.getInt("person_id"), rs.getString("person_name")),
                        filter.getMark(),
                        filter.getModel(),
                        rs.getTimestamp("assembledate"),
                        filter.getEngine(),
                        rs.getInt("numberofseats"),
                        filter.getColor()
                ));
            }

            return ls;

        }catch (Exception ex){
        }

        return null;
    }
}
