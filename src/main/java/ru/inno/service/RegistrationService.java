package ru.inno.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.inno.dao.*;
import java.sql.SQLException;


@Service
public class RegistrationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationService.class);

    private PersonDAO dao;
    private PasswordEncoder encoder;

    public RegistrationService() {}

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Autowired
    public void setDao(PersonDAO dao) {
        this.dao = dao;
    }

    public PasswordEncoder getEncoder() {
        return encoder;
    }

    public PersonDAO getDao() {
        return dao;
    }

    public boolean addUser(String name, String login, String password){

        String passwordHash = encoder.encode(password);
        boolean result = false;
        if (dao.getPerson(login) == null){
            int id = dao.addPerson(name, login, passwordHash, "ROLE_USER");
            if (id != 0){
                result = true;
            }
        }

        return result;
    }
}
