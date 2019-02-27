package ru.inno.repository;

import ru.inno.dao.PersonDAO;
import ru.inno.entity.Person;

import java.sql.SQLException;
import java.util.List;

public class PersonHiber implements PersonDAO {
    @Override
    public List<Person> getPersons() {
        return null;
    }

    @Override
    public Person getPerson(int id) {
        return null;
    }

    @Override
    public Person getPerson(String login) {
        return null;
    }

    @Override
    public int addPerson(String name, String login, String pass, String role) throws SQLException {
        return 0;
    }
}
