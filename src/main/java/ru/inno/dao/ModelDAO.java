package ru.inno.dao;

import ru.inno.entity.Model;

import java.util.Collection;

public interface ModelDAO {
    Collection<Model> getModels();
    Model getModel(int id);
    /**
     *
     * Добавляем модель в базу
     * @param name
     */
    void addModel(int id_mark, String name);
}
