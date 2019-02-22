package ru.inno.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.inno.ConnectionManager;
import ru.inno.dao.*;

import java.sql.Connection;
import java.sql.SQLException;


@Service
public class RegistrationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EngineImpl.class);

    private PersonDAO dao;

    public PersonDAO getDao() {
        return dao;
    }

    public void setDao(PersonDAO dao) {
        this.dao = dao;
    }

    public void addUser(String name, String login, String password){
        System.out.println("service");

        Connection c = ConnectionManager.getConnection();
        dao = new PersonImpl(c);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordHash = encoder.encode(password);
        try {
            dao.addPerson(name, login, passwordHash, "ROLE_USER");
        } catch (SQLException e) {
            LOGGER.error("add user sql error ");
        }
        ConnectionManager.closeConnection(c);
    }
}
