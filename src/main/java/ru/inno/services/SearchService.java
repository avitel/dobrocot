package ru.inno.services;

import ru.inno.ConnectionController;
import ru.inno.dao.*;
import ru.inno.entity.*;

import java.util.*;

public class SearchService {

    private static ConnectionController connectionController = ConnectionController.createController();


    public Map<String, Object> getFilterOption() {
        Map<String, Object> result = new HashMap<>();
        connectionController.setConnection();
        MarkDAO markDAO = new MarkImpl(connectionController.getConnection());
        ModelDAO modelDAO = new ModelImpl(connectionController.getConnection());
        ColorDAO colorDAO = new ColorImpl(connectionController.getConnection());
        EngineDAO engineDAO = new EngineImpl(connectionController.getConnection());

        Collection<Mark> marks = markDAO.getMarks();
        Collection<Model> models = modelDAO.getModels(null);
        Collection<Color> colors = colorDAO.getColors();
        Collection<Engine> engines = engineDAO.getEngines();

        result.put("marks", marks);
        result.put("models", models);
        result.put("colors", colors);
        result.put("engines", engines);

        return result;
    }

    public List<Car> getFilteredCars(Integer mark_id, Integer model_id, Integer engine_id, Integer color_id) {
        QueryBuilder builder = new QueryBuilder(color_id, engine_id, mark_id, model_id);
        CarDAO carDAO = new CarImpl(connectionController.getConnection());
        return carDAO.getFilteredCars(builder);

    }

}
