package ru.inno.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.inno.dao.*;
import ru.inno.entity.*;
import ru.inno.repository.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AddNewCarServiceTest {

    private CarDAO mockCar;
    private MarkDAO mockMark;
    private ModelDAO mockModel;
    private EngineDAO mockEngine;
    private ColorDAO mockColor;

    private List<Car> cars;
    private List<Mark> marks;
    private List<Model> models;
    private List<Color> colors;
    private List<Engine> engine;

    private AddNewCarService addNewCarService;

    private SimpleDateFormat sdf;

    @BeforeEach
    void setUp() {
        mockCar = Mockito.mock(CarHiber.class);
        mockMark = Mockito.mock(MarkHiber.class);
        mockModel = Mockito.mock(ModelHiber.class);
        mockEngine = Mockito.mock(EngineHiber.class);
        mockColor = Mockito.mock(ColorHiber.class);

        addNewCarService = new AddNewCarService(mockCar, mockMark, mockModel, mockEngine, mockColor);
        cars = Collections.singletonList(new Car());
        marks = Collections.singletonList(new Mark());
        models = Collections.singletonList(new Model());
        colors = Collections.singletonList(new Color());
        engine = Collections.singletonList(new Engine());

        sdf = new SimpleDateFormat("yyyy");
    }

    @Test
    public void getParam() {
        when(mockMark.getMarks()).thenReturn(marks);
        when(mockColor.getColors()).thenReturn(colors);
        when(mockEngine.getEngines()).thenReturn(engine);
        when(mockModel.getModels(null)).thenReturn(models);

        Map<String, Object> result = addNewCarService.getParam();
        assertEquals(marks, result.get("marks"));
        assertEquals(models, result.get("models"));
        assertEquals(colors, result.get("colors"));
        assertEquals(engine, result.get("engines"));

    }

    @Test
    public void addCar() throws ParseException {
        when(mockCar.addCar(1, 1, 1, new Timestamp(0), 1, 1, 1, 1)).thenReturn(1);

        addNewCarService.addCar(1, "1", "1", "1999", "1", "1", "1", "1");

        verify(mockCar, times(1))
                .addCar(1, 1, 1, new Timestamp(sdf.parse("1999").getTime()), 1, 1, 1, 1);
    }
}
