package ru.inno.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.inno.dao.*;
import ru.inno.entity.Person;

import java.sql.Connection;
import java.sql.SQLException;


@Service
public class RegistrationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EngineImpl.class);

    private PersonDAO dao;

    @Autowired
    public void setDao(PersonDAO dao) {
        this.dao = dao;
    }

    public boolean addUser(String name, String login, String password){

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordHash = encoder.encode(password);
        boolean result = false;
        try {
            if (dao.getPerson(login) == null){
                int id = dao.addPerson(name, login, passwordHash, "ROLE_USER");
                if (id != 0){
                    result = true;
                }
            }

        } catch (SQLException e) {
            LOGGER.error("add user sql error",e);
        }
        return result;
    }
}
