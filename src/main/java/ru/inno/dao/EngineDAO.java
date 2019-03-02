package ru.inno.dao;

import ru.inno.entity.Engine;

import java.util.List;

public interface EngineDAO {
    List<Engine> getEngines();
    Engine getEngine(int id);
    /**
     *
     * Добавляем двигатель в базу
     * @param name
     */
    int addEngine(String name);
//    void deleteEngine(Engine engine);
}
