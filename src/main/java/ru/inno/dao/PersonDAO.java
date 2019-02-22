package ru.inno.dao;

import ru.inno.entity.Person;

import java.sql.SQLException;
import java.util.Collection;

public interface PersonDAO {

    Collection<Person> getPersons();

    Person getPerson(int id);

    Person getPerson(String login);

    int addPerson(String name, String login, String pass, String role) throws SQLException;
}
