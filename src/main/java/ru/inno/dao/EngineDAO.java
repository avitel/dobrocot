package ru.inno.dao;

import ru.inno.entity.Engine;

import java.util.Collection;

public interface EngineDAO {
    Collection<Engine> getEngines();
    Engine getEngine(int id);
    /**
     *
     * Добавляем модель в базу
     * @param name
     */
    void addEngine(String name);
    void deleteEngine(Engine engine);
}
