package ru.inno.services;

//import ru.inno.ConnectionManager;
import org.springframework.stereotype.Service;
import ru.inno.dao.*;
import ru.inno.entity.*;

import java.sql.Timestamp;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddNewCarService {

    private CarDAO carDAO;
    private MarkDAO markDAO;
    private ModelDAO modelDAO;
    private EngineDAO engineDAO;
    private ColorDAO colorDAO;

    public AddNewCarService(CarDAO carDAO, MarkDAO markDAO, ModelDAO modelDAO, EngineDAO engineDAO, ColorDAO colorDAO) {
        this.carDAO = carDAO;
        this.markDAO = markDAO;
        this.modelDAO = modelDAO;
        this.engineDAO = engineDAO;
        this.colorDAO = colorDAO;
    }

    public Map<String, Object> getParam() {
//        Connection c = ConnectionManager.getConnection();
        Map<String, Object> allParams = new HashMap<>();
//        MarkDAO markDAO = new MarkImpl(c);
//        ModelDAO modelDAO = new ModelImpl(c);
//        EngineDAO engineDAO = new EngineImpl(c);
//        ColorDAO colorDAO = new ColorImpl(c);

        List<Mark> marks = markDAO.getMarks();
        List<Model> models = modelDAO.getModels(null);
        List<Engine> engines = engineDAO.getEngines();
        List<Color> colors = colorDAO.getColors();
//        ConnectionManager.closeConnection(c);

        allParams.put("marks", marks);
        allParams.put("models", models);
        allParams.put("colors", colors);
        allParams.put("engines", engines);

        return allParams;
    }

    public boolean addCar(String mark_id, String model_id,
                         Timestamp assembledate, String engine_id, String numbeerofseats, String color) {
//        Connection c = ConnectionManager.getConnection();
//        CarDAO carDAO = new CarImpl(c);

        //Person person = (Person) authentication.getPrincipal();

//        carDAO.addCar(person.getId(), mark_id, model_id, assembledate,
//                engine_id), numbeerofseats);

//        ConnectionManager.closeConnection(c);
        return true;
    }
}
