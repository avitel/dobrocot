package ru.inno.dao;

import ru.inno.entity.Class;

import java.util.Collection;

public interface ClassDAO {
    Collection<Class> getClasses();
    Class getClass(int id);
    /**
     *
     * Добавляем цвет в базу
     * @param name
     */
    void addClass(String name);
}
