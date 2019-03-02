package ru.inno.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.inno.service.RegistrationService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class RegistrationControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {

        RegistrationService registrationService = Mockito.mock(RegistrationService.class);

        Mockito.when(registrationService.addUser("a", "a","a")).thenReturn(true);
        Mockito.when(registrationService.addUser("b", "b","b")).thenReturn(false);

        RegistrationController registrationController = new RegistrationController(registrationService);

        this.mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
    }

    @Test
    void getForm() throws Exception {

        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration_"));
    }


    @Test
    void addPerson() throws Exception {

        mockMvc.perform(post("/registration")
                .param("name", "a")
                .param("login", "a")
                .param("pass", "a"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }


    @Test
    void addPersonCaseUserAlreadyExist() throws Exception {

        mockMvc.perform(post("/registration")
                .param("name", "b")
                .param("login", "b")
                .param("pass", "b"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("WarningMessage"))
                .andExpect(view().name("registration_"));
    }


    @Test
    void addPersonNegative() throws Exception {

        mockMvc.perform(post("/registration"))
                .andExpect(status().isBadRequest());

    }

}
