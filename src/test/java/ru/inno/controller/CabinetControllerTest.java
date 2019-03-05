package ru.inno.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.inno.entity.Car;
import ru.inno.entity.Order;
import ru.inno.entity.Person;
import ru.inno.service.CabinetService;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CabinetControllerTest {

    @Mock
    private CabinetService cabinetService;
    @InjectMocks
    private CabinetController cabinetController;
    private List<Order> orders;
    private MockMvc mockMvc;
    private List<Car> cars;
    private Person person;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cabinetController).build();

        person = new Person();
        cars = Collections.singletonList(new Car());
        orders = Collections.singletonList(new Order());
    }

    @Test
    void cabinetGET() throws Exception {
        when(cabinetService.getCurrentPerson()).thenReturn(person);
        when(cabinetService.getCarList(any(Person.class))).thenReturn(cars);
        when(cabinetService.getSellerOrders(any(Person.class))).thenReturn(orders);
        when(cabinetService.getCustomerOrders(any(Person.class))).thenReturn(orders);

        mockMvc.perform(get("/cabinet/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("cabinet"))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("carList", "customerOrders", "sellerOrders", "currentPerson"))
                .andExpect(model().attribute("carList", cars))
                .andExpect(model().attribute("customerOrders", orders))
                .andExpect(model().attribute("sellerOrders", orders))
                .andExpect(model().attribute("currentPerson", person)
                );
    }

    @Test
    void cabinetPOST() throws Exception {
        when(cabinetService.getCurrentPerson()).thenReturn(person);
        when(cabinetService.getCarList(any(Person.class))).thenReturn(cars);
        when(cabinetService.getSellerOrders(any(Person.class))).thenReturn(orders);
        when(cabinetService.getCustomerOrders(any(Person.class))).thenReturn(orders);
        when(cabinetService.deleteCar(2)).thenReturn(true);

        mockMvc.perform(post("/cabinet/").param("car_id","2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors());
    }
}