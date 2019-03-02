package ru.inno.dao;

import ru.inno.entity.Mark;

import java.util.List;

public interface MarkDAO {

    List<Mark> getMarks();

    Mark getMark(int id);

    int addMark(String name);
}
