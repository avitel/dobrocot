package ru.inno.dao;

import ru.inno.entity.Mark;

import java.util.List;

public interface MarkDAO {



    List<Mark> getMarks();
    Mark getMark(int id);
    /**
     *
     * Добавляем марку в базу
     * @param name
     */
    int addMark(String name);
//    void deleteMark(Mark mark);
}
