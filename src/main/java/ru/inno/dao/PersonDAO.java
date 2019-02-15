package ru.inno.dao;

import ru.inno.entity.Person;

import java.util.Collection;

public interface PersonDAO {
    Collection<Person> getPersons();
    Person getPerson(int id);
    /**
     *
     * Добавляем персону в базу
     * @param name
     */
    void addPerson(String name);
//    void deletePerson(Person person);
}
