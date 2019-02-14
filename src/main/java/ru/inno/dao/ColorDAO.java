package ru.inno.dao;

import ru.inno.entity.Color;

import java.util.Collection;

public interface ColorDAO {
    Collection<Color> getColors();
    Color getColor(int id);
    /**
     *
     * Добавляем цвет в базу
     * @param name
     */
    void addColor(String name);
}
