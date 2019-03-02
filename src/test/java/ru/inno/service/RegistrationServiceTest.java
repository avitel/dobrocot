package ru.inno.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.inno.dao.PersonDAO;
import ru.inno.entity.Person;
import ru.inno.repository.PersonHiber;


import static org.junit.jupiter.api.Assertions.*;

class RegistrationServiceTest {

    private RegistrationService registrationService;

    @BeforeEach
    void setUp() {
        registrationService = new RegistrationService();
    }

    @Test
    void setDao() {
        PersonDAO inject = new PersonHiber();
        registrationService.setDao(inject);
        assertEquals(inject, registrationService.getDao());
    }

    @Test
    void setEncoder() {
        PasswordEncoder inject = new BCryptPasswordEncoder();
        registrationService.setEncoder(inject);
        assertEquals(inject, registrationService.getEncoder());
    }

    @Test
    void addUser() {
        PersonDAO mockDao = Mockito.mock(PersonHiber.class);
        Mockito.when(mockDao.getPerson("login1")).thenReturn(null);
        Mockito.when(mockDao.addPerson(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(1);

        registrationService.setDao(mockDao);
        registrationService.setEncoder(new BCryptPasswordEncoder());

        assertEquals(true, registrationService.addUser("mock","login1","mock"));
    }


    @Test
    void addUserCaseAlreadyExist() {
        PersonDAO mockDao = Mockito.mock(PersonHiber.class);
        Mockito.when(mockDao.getPerson("login2")).thenReturn(new Person());
        Mockito.when(mockDao.addPerson(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(1);

        registrationService.setDao(mockDao);
        registrationService.setEncoder(new BCryptPasswordEncoder());

        assertEquals(false, registrationService.addUser("mock","login2","mock"));
    }


    @Test
    void addUserNegative() {
        PersonDAO mockDao = Mockito.mock(PersonHiber.class);
        Mockito.when(mockDao.getPerson("login1")).thenReturn(null);
        Mockito.when(mockDao.addPerson(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(1);

        registrationService.setDao(mockDao);
        registrationService.setEncoder(new BCryptPasswordEncoder());

        assertThrows(NullPointerException.class, () -> registrationService.addUser(null,null,null));
    }
}

