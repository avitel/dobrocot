package ru.inno.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.inno.entity.Car;
import ru.inno.entity.Person;
import ru.inno.service.MainService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class MainControllerTest {

    @Mock
    private MainService mainService;
    @InjectMocks
    private MainController mainController;
    private Map<String, Object> allFilterOption;
    private List<Car> filteredCars;
    private MockMvc mockMvc;
    private Person person;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();

        allFilterOption = Collections.singletonMap("", new Object());
        person = new Person(1, "", "", "", "");
        filteredCars = Collections.singletonList(new Car());
    }

    @Test
    void mainPageGET() throws Exception {

        when(mainService.getAllFilterOption()).thenReturn(allFilterOption);
        when(mainService.getCurrentPerson()).thenReturn(person);
        when(mainService.getFilteredCars(null, null, null, null)).thenReturn(filteredCars);

        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(MainController.MAINPAGE))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists(MainController.USER_ATTRIBUTE, MainController.FILTER_OPTION_ATTRIBUTE, MainController.RESULT_ATTRIBUTE))
                .andExpect(model().attribute(MainController.USER_ATTRIBUTE, person.getName()))
                .andExpect(model().attribute(MainController.FILTER_OPTION_ATTRIBUTE, allFilterOption))
                .andExpect(model().attribute(MainController.RESULT_ATTRIBUTE, filteredCars)
                );

    }

    @Test
    void mainPagePOST() throws Exception {
        when(mainService.getAllFilterOption()).thenReturn(allFilterOption);
        when(mainService.getCurrentPerson()).thenReturn(person);
        when(mainService.getFilteredCars(1, 1, 1, 1)).thenReturn(filteredCars);

        mockMvc.perform(
                post("/")
                        .param(MainController.MARK_REQUEST, "1")
                        .param(MainController.MODEL_REQUEST, "1")
                        .param(MainController.COLOR_REQUEST, "1")
                        .param(MainController.ENGINE_REQUEST, "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(MainController.MAINPAGE))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists(MainController.USER_ATTRIBUTE, MainController.FILTER_OPTION_ATTRIBUTE, MainController.RESULT_ATTRIBUTE))
                .andExpect(model().attribute(MainController.USER_ATTRIBUTE, person.getName()))
                .andExpect(model().attribute(MainController.FILTER_OPTION_ATTRIBUTE, allFilterOption))
                .andExpect(model().attribute(MainController.RESULT_ATTRIBUTE, filteredCars)
                );
    }
}