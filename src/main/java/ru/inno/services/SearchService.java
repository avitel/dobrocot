package ru.inno.services;

import ru.inno.ConnectionManager;
import ru.inno.dao.*;
import ru.inno.entity.*;

import java.sql.Connection;
import java.util.*;

public class SearchService {

    public Map<String, Object> getFilterOption() {
        Map<String, Object> result = new HashMap<>();
        Connection c = ConnectionManager.getConnection();
        MarkDAO markDAO = new MarkImpl(c);
        ModelDAO modelDAO = new ModelImpl(c);
        ColorDAO colorDAO = new ColorImpl(c);
        EngineDAO engineDAO = new EngineImpl(c);

        Collection<Mark> marks = markDAO.getMarks();
        Collection<Model> models = modelDAO.getModels(null);
        Collection<Color> colors = colorDAO.getColors();
        Collection<Engine> engines = engineDAO.getEngines();

        result.put("marks", marks);
        result.put("models", models);
        result.put("colors", colors);
        result.put("engines", engines);
        ConnectionManager.closeConnection(c);
        return result;
    }

    public List<Car> getFilteredCars(Integer mark_id, Integer model_id, Integer engine_id, Integer color_id) {
        QueryBuilder builder = new QueryBuilder(color_id, engine_id, mark_id, model_id);
        Connection c = ConnectionManager.getConnection();
        CarDAO carDAO = new CarImpl(c);

        List<Car> cars = carDAO.getFilteredCars(builder);
        ConnectionManager.closeConnection(c);
        return cars;

    }

}
