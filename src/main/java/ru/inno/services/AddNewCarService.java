package ru.inno.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.inno.ConnectionManager;
import ru.inno.dao.*;
import ru.inno.entity.*;

import java.sql.Timestamp;
import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Component
public class AddNewCarService {

    public Map<String, Object> getParam() {
        Connection c = ConnectionManager.getConnection();
        Map<String, Object> allParams = new HashMap<>();
        MarkDAO markDAO = new MarkImpl(c);
        ModelDAO modelDAO = new ModelImpl(c);
        EngineDAO engineDAO = new EngineImpl(c);
        ColorDAO colorDAO = new ColorImpl(c);

        Collection<Mark> marks = markDAO.getMarks();
        Collection<Model> models = modelDAO.getModels(null);
        Collection<Engine> engines = engineDAO.getEngines();
        Collection<Color> colors = colorDAO.getColors();
        ConnectionManager.closeConnection(c);

        allParams.put("marks", marks);
        allParams.put("models", models);
        allParams.put("colors", colors);
        allParams.put("engines", engines);

        return allParams;
    }

    public boolean addCar(String mark_id, String model_id,
                         Timestamp assembledate, String engine_id, String numbeerofseats, String color) {
        Connection c = ConnectionManager.getConnection();
        CarDAO carDAO = new CarImpl(c);

        //Person person = (Person) authentication.getPrincipal();

//        carDAO.addCar(person.getId(), mark_id, model_id, assembledate,
//                engine_id), numbeerofseats);

        ConnectionManager.closeConnection(c);
        return true;
    }
}
