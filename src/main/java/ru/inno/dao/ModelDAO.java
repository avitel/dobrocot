package ru.inno.dao;

import ru.inno.entity.Model;

import java.util.List;

public interface ModelDAO {

    List<Model> getModels(Integer mark_id);

    Model getModel(int id);

    int addModel(int id_mark, String name);
}
