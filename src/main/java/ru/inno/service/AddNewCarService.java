package ru.inno.service;

import org.springframework.stereotype.Service;
import ru.inno.dao.*;
import ru.inno.entity.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import static java.lang.Integer.parseInt;

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
        Map<String, Object> allParams = new HashMap<>();

        List<Mark> marks = markDAO.getMarks();
        List<Model> models = modelDAO.getModels(null);
        List<Engine> engines = engineDAO.getEngines();
        List<Color> colors = colorDAO.getColors();

        allParams.put("marks", marks);
        allParams.put("models", models);
        allParams.put("colors", colors);
        allParams.put("engines", engines);

        return allParams;
    }

    public void addCar(int getId, String mark_id, String model_id, String assembledate,
                       String engine_id, String numbeerofseats, String color, String dayprice) {


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        Date parsedDate = null;
        try {
            parsedDate = simpleDateFormat.parse(assembledate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp timestamp = new Timestamp(parsedDate.getTime());
        carDAO.addCar(getId, parseInt(mark_id), parseInt(model_id), timestamp,
                parseInt(engine_id), parseInt(numbeerofseats), parseInt(color), parseInt(dayprice));

    }
}
