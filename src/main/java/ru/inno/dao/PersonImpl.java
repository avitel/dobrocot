package ru.inno.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.inno.entity.Engine;
import ru.inno.entity.Person;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class PersonImpl implements PersonDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonImpl.class);

    public static final String CREAT_PERSON_SQL_TEMPLATE =
            "insert into person (name, login, password, role) values (?,?,?,?) returning id";

    public static final String GET_PERSON_SQL_TEMPLATE =
            "select id, name, login from person where id = ?";

    public static final String GET_PERSON_BY_LOGIN_SQL_TEMPLATE =
            "select id, name, login, role from person where login = ?";

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
                persons.add(new Person(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)));
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
            person = new Person(id, i, rs.getString(3));
        }catch (Exception ex){
            LOGGER.debug(ex.getMessage());
            LOGGER.error("Ошибка при получении персоны!");
        }
        return person;
    }



    @Override
    public Person getPerson(String login) {

        try (PreparedStatement statement = connection.prepareStatement(GET_PERSON_BY_LOGIN_SQL_TEMPLATE)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Person(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("role"));
            }else {
                return new Person(rs.getInt(""),
                        rs.getString(""),
                        rs.getString(""),
                        rs.getString(""));
            }

        } catch (Exception ex) {
            LOGGER.debug(ex.getMessage());
            LOGGER.error("get person query error");
        }
        return null;
    }



    @Override
    public int addPerson(String name, String login, String password, String role) throws SQLException {
        System.out.println("in the begin");
        try (PreparedStatement statement = connection.prepareStatement(CREAT_PERSON_SQL_TEMPLATE)) {
            statement.setString(1, name);
            statement.setString(2, login);
            statement.setString(3, password);
            statement.setString(4, role);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                LOGGER.info("person {} ({}) was created", name, login);
                return rs.getInt("id");
            }else {
                LOGGER.error("returning id fail");
                throw new SQLDataException("something went wrong");
            }
        }catch (Exception e){
            LOGGER.error("sql error",e);
            return 0;
        }
    }
}
