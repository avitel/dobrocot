package ru.inno.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.entity.Engine;
import ru.inno.entity.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class PersonImpl implements PersonDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(EngineImpl.class);

    public static final String INSERT_PERSON_SQL_TEMPLATE =
            "insert into person (name) values (?) returning id";

    public static final String GET_PERSON_SQL_TEMPLATE =
            "select name from person where id = ?";

    public static final String GET_PERSONS_SQL_TEMPLATE =
            "select id, name from person";


    private final Connection connection;

    /**
     * Получает подключение и сохраняет в данном объекте.
     * @param connection
     */
    public PersonImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Collection<Person> getPersons() {

        Collection<Person> persons = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs;
            rs = statement.executeQuery(GET_PERSONS_SQL_TEMPLATE);
            while (rs.next()){
                persons.add(new Person(rs.getInt(1),rs.getString(2)));
            }
            if(persons.size() == 0){
                LOGGER.info("Список персон пуст!");
            }
        }catch (Exception ex){
            LOGGER.debug(ex.getMessage());
            LOGGER.error("Ошибка при получении списка персон!");
        }
        return persons;
    }

    @Override
    public Person getPerson(int id) {

        Person person = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_PERSON_SQL_TEMPLATE)) {
            statement.setInt(1,id);
            String i = null;
            ResultSet rs;
            rs = statement.executeQuery();
            rs.next();
            i = rs.getString(1);
            if (i==null) {
                throw new SQLException();
            }
            person = new Person(id, i);
        }catch (Exception ex){
            LOGGER.debug(ex.getMessage());
            LOGGER.error("Ошибка при получении персоны!");
        }
        return person;
    }

    @Override
    public void addPerson(String name) {
        int i = -1;
        try (PreparedStatement statement = connection.prepareStatement(INSERT_PERSON_SQL_TEMPLATE)) {
            statement.setString(1, name);
            ResultSet rs;
            rs = statement.executeQuery();
            rs.next();
            i = rs.getInt(1);
            if (i<0) {
                throw new SQLException();
            }
            connection.commit();
            LOGGER.info("Персона " + name + " успешно добавлена.");
        }catch (Exception ex){
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            LOGGER.debug(ex.getMessage());
            LOGGER.error("Ошибка при создании персоны!");
        }
    }
}
