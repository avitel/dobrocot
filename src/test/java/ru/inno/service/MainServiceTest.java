package ru.inno.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.inno.Security;
import ru.inno.controller.MainController;
import ru.inno.dao.*;
import ru.inno.entity.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MainServiceTest {

    private Security security;
    private MainService mainService;
    private EngineDAO engineDAO;
    private ColorDAO colorDAO;
    private ModelDAO modelDAO;
    private MarkDAO markDAO;
    private CarDAO carDAO;


    private Person person;
    private List<Car> cars;
    private List<Mark> marks;
    private List<Model> models;
    private List<Color> colors;
    private List<Engine> engines;


    @BeforeEach
    void setUp() {
        carDAO = mock(CarDAO.class);
        markDAO = mock(MarkDAO.class);
        modelDAO = mock(ModelDAO.class);
        colorDAO = mock(ColorDAO.class);
        security = mock(Security.class);
        engineDAO = mock(EngineDAO.class);
        mainService = new MainService(carDAO, markDAO, modelDAO, colorDAO, engineDAO, security);


        person = new Person();
        cars = Collections.singletonList(new Car());
        marks = Collections.singletonList(new Mark());
        models = Collections.singletonList(new Model());
        colors = Collections.singletonList(new Color());
        engines = Collections.singletonList(new Engine());
    }

    @Test
    void getAllFilterOption() {
        when(markDAO.getMarks()).thenReturn(marks);
        when(colorDAO.getColors()).thenReturn(colors);
        when(engineDAO.getEngines()).thenReturn(engines);
        when(modelDAO.getModels(null)).thenReturn(models);

        Map<String, Object> result = mainService.getAllFilterOption();

        assertEquals(marks, result.get(MainController.MARK_REQUEST));
        assertEquals(models, result.get(MainController.MODEL_REQUEST));
        assertEquals(colors, result.get(MainController.COLOR_REQUEST));
        assertEquals(engines, result.get(MainController.ENGINE_REQUEST));
    }

    @Test
    void getFilteredCars() {
        when(carDAO.getFilteredCars(any())).thenReturn(cars);
        assertEquals(cars, mainService.getFilteredCars(1, 1, 1, 1));
    }

    @Test
    void getCurrentPerson() {
        when(security.getCurrentUser()).thenReturn(person);
        assertEquals(person, mainService.getCurrentPerson());
    }
}