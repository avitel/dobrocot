package ru.inno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inno.Security;
import ru.inno.controller.MainController;
import ru.inno.dao.*;
import ru.inno.entity.Car;
import ru.inno.entity.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MainService {

    private CarDAO carDAO;
    private MarkDAO markDAO;
    private ModelDAO modelDAO;
    private ColorDAO colorDAO;
    private EngineDAO engineDAO;
    private Security security;

    public MainService() {
    }

    @Autowired
    public MainService(CarDAO carDAO, MarkDAO markDAO, ModelDAO modelDAO, ColorDAO colorDAO, EngineDAO engineDAO, Security security) {
        this.carDAO = carDAO;
        this.markDAO = markDAO;
        this.modelDAO = modelDAO;
        this.colorDAO = colorDAO;
        this.engineDAO = engineDAO;
        this.security = security;
    }


    public Map<String, Object> getAllFilterOption() {
        Map<String, Object> result = new HashMap<>();
        result.put(MainController.MARK_REQUEST, markDAO.getMarks());
        result.put(MainController.MODEL_REQUEST, modelDAO.getModels(null));
        result.put(MainController.COLOR_REQUEST, colorDAO.getColors());
        result.put(MainController.ENGINE_REQUEST, engineDAO.getEngines());
        return result;
    }

    public List<Car> getFilteredCars(Integer mark_id, Integer model_id, Integer engine_id, Integer color_id) {
        QueryBuilder builder = new QueryBuilder(color_id, engine_id, mark_id, model_id);
        return carDAO.getFilteredCars(builder);
    }

    public Person getCurrentPerson() {
        return security.getCurrentUser();
    }

}
