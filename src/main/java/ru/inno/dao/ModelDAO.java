package ru.inno.dao;

import java.util.Collection;

public interface ModelDAO {
    Collection<ModelDAO> getModels();
    ModelDAO getModel(int id);
    /**
     *
     * Добавляем модель в базу
     * @param name
     */
    void addModel(String name);
}
