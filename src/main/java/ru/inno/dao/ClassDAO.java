package ru.inno.dao;

import ru.inno.entity.Class;

import java.util.List;

public interface ClassDAO {
    List<Class> getClasses();
    Class getClass(int id);
    /**
     *
     * Добавляем класс в базу
     * @param name
     */
    void addClass(String name);
}
