package ru.inno.dao;

import ru.inno.entity.Mark;

import java.util.Collection;

public interface MarkDAO {



    Collection<Mark> getMarks();
    Mark getMark(int id);
    /**
     *
     * Добавляем модель в базу
     * @param name
     */
    void addMark(String name);
    void deleteMark(Mark mark);
}
