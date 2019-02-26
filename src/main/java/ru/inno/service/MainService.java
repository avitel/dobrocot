package ru.inno.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.inno.Security;
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
    private PersonDAO personDAO;

    public MainService(CarDAO carDAO, MarkDAO markDAO, ModelDAO modelDAO, ColorDAO colorDAO, EngineDAO engineDAO, PersonDAO personDAO) {
        this.carDAO = carDAO;
        this.markDAO = markDAO;
        this.modelDAO = modelDAO;
        this.colorDAO = colorDAO;
        this.engineDAO = engineDAO;
        this.personDAO = personDAO;
    }


    public Map<String, Object> getAllFilterOption() {
        Map<String, Object> result = new HashMap<>();
        result.put("marks", markDAO.getMarks());
        result.put("models", modelDAO.getModels(null));
        result.put("colors", colorDAO.getColors());
        result.put("engines", engineDAO.getEngines());
        return result;
    }

    public List<Car> getFilteredCars(Integer mark_id, Integer model_id, Integer engine_id, Integer color_id) {
        QueryBuilder builder = new QueryBuilder(color_id, engine_id, mark_id, model_id);
        return carDAO.getFilteredCars(builder);
    }

    public Person getCurrentPerson() {
        Security security = new Security(SecurityContextHolder.getContext(), personDAO);
        return security.getCurrentUser();
    }

}
