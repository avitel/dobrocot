package ru.inno.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.inno.Security;
import ru.inno.entity.Person;
import ru.inno.service.AddNewCarService;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AddNewCarControllerTest {

    private MockMvc mockMvc;


    @BeforeEach
    public void setup() {
        AddNewCarService addNewCarService = Mockito.mock(AddNewCarService.class);
        Security security = Mockito.mock(Security.class);

        Map<String, Object> map = new HashMap<>();
        Mockito.when(addNewCarService.getParam()).thenReturn(map);

        Mockito.when(security.getCurrentUser()).thenReturn(new Person(1,"1","1"));

      Mockito.doNothing().when(addNewCarService).addCar(isA(Integer.class), isA(String.class), isA(String.class), isA(String.class),
              isA(String.class),isA(String.class),isA(String.class),isA(String.class));


        AddNewCarController addNewCarController = new AddNewCarController(addNewCarService, security);
        this.mockMvc = MockMvcBuilders.standaloneSetup(addNewCarController).build();
    }

    @Test
    public void getPage() throws Exception {

        mockMvc.perform(get("/addnewcar"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("addnewcar_"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("param"));


    }

    @Test
    public void addNewCar() throws Exception {


        mockMvc.perform(post("/addnewcar")
                .param("mark", "1")
                .param("model", "1")
                .param("assembledate", "2019")
                .param("engine", "2.5")
                .param("numbeerofseats", "4")
                .param("color", "white")
                .param("dayprice", "1000"))
                .andExpect(view().name("redirect:/cabinet"));
                //.andExpect(model().attributeExists());

    }
}