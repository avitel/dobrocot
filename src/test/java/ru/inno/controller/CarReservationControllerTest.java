package ru.inno.controller;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ru.inno.service.CarReservationService;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CarReservationControllerTest {
    CarReservationService mockserv = Mockito.mock(CarReservationService.class);
    private MockMvc mockmvc;
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    CarReservationController crc;
    @BeforeEach
    void setUp() throws Exception {

        mockserv = Mockito.mock(CarReservationService.class);
        Mockito.when(mockserv.getDays(Mockito.anyObject(), Mockito.anyObject())).thenReturn(5);
        Map<String,String> mapa = new HashMap<>();
        mapa.put("dayprice","100");
        Mockito.when(mockserv.getValues(Mockito.anyString())).thenReturn(mapa);
        crc = new CarReservationController(mockserv);
        viewResolver.setPrefix("/webapp/pages/");
        viewResolver.setSuffix(".jsp");
        mockmvc = MockMvcBuilders.standaloneSetup(crc)
                .setViewResolvers(viewResolver)
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void doReservePositive() throws Exception {
                int status = mockmvc.perform(post("/carreserve").param("car_id","2"))
                .andDo(print())
                .andExpect(forwardedUrl("/webapp/pages/carreserve.jsp"))
                .andReturn()
                .getResponse().getStatus();
                Assertions.assertEquals(200,status);
    }

    @Test
    void doReserveNegative() throws Exception {
        int status = mockmvc.perform(post("/carreserve"))
                .andDo(print())
                .andReturn()
                .getResponse()
                .getStatus();
        Assertions.assertNotEquals(200,status);
    }

    @Test
    void doReserve2Positive() throws Exception {
        int status = mockmvc.perform(post("/carreserve2")
                .param("car_id","2")
                .param("date_begin","2019-04-01")
                .param("date_end","2019-05-01")
                .param("id_owner","5")
                .param("price","500"))
                .andDo(print())
                .andExpect(forwardedUrl("/webapp/pages/carreserve.jsp"))
                .andReturn()
                .getResponse().getStatus();
        Assertions.assertEquals(200,status);
    }

    @Test
    void doReserve2Negative() throws Exception {
        int status = mockmvc.perform(post("/carreserve2")
                .param("car_id","2")
                .param("date_begin","2019-04-01")
                .param("date_end","2019-05-01")
                .param("id_owner","5"))
                .andDo(print())
                .andReturn()
                .getResponse().getStatus();
        Assertions.assertEquals(400,status);
    }

    @Test
    void doReserve3Positive() throws Exception {
        int status = mockmvc.perform(post("/carreserve3")
                .param("car_id","2")
                .param("date_begin","2019-04-01").requestAttr("dayprice","500")
                .requestAttr("days",5)
                .param("date_end","2019-05-01"))
                .andExpect(model().attribute("date_begin","2019-04-01"))
                .andExpect(model().attribute("date_end","2019-05-01"))
                .andDo(print())
                .andExpect(forwardedUrl("/webapp/pages/carreserve.jsp"))
                .andReturn()
                .getResponse().getStatus();
        Assertions.assertEquals(200,status);
    }

    @Test
    void doReserve3Negative() throws Exception {
        int status = mockmvc.perform(post("/carreserve3")
//                .param(model,Mockito.mock(org.springframework.ui.Model.class))
                .param("car_id","2")
                .param("date_end","2019-04-01"))
                .andDo(print())
                .andReturn()
                .getResponse().getStatus();
        Assertions.assertEquals(400,status);
    }
}