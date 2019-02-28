package ru.inno.dao;

import ru.inno.entity.Color;

import java.util.List;

public interface ColorDAO {
    List<Color> getColors();
    Color getColor(int id);
    /**
     *
     * Добавляем цвет в базу
     * @param name
     */
    int addColor(String name);
//    void deleteColor(Color color);
}
