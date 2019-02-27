package ru.inno.services;

import org.springframework.stereotype.Component;
import ru.inno.ConnectionManager;
import ru.inno.dao.*;
import ru.inno.entity.Color;
import ru.inno.entity.Engine;
import ru.inno.entity.Mark;
import ru.inno.entity.Model;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

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

    public void addCar(int getId, String mark_id, String model_id,
                               String assembledate, String engine_id, String numbeerofseats, String color) {
        Connection c = ConnectionManager.getConnection();
        CarDAO carDAO = new CarImpl(c);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        Date parsedDate = null;
        try {
            parsedDate = simpleDateFormat.parse(assembledate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
        carDAO.addCar(getId, parseInt(mark_id), parseInt(model_id), timestamp,
                parseInt(engine_id), parseInt(numbeerofseats), parseInt(color));

        ConnectionManager.closeConnection(c);
    }
}
