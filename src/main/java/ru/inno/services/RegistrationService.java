package ru.inno.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.inno.ConnectionManager;
import ru.inno.dao.*;

import java.sql.Connection;
import java.sql.SQLException;



public class RegistrationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EngineImpl.class);


    public void addUser(String name, String login, String password, String isseller, String iscustomer){
        System.out.println("service");

        Connection c = ConnectionManager.getConnection();
        PersonDAO dao = new PersonImpl(c);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordHash = encoder.encode(password);
        try {
            dao.addPerson(name, login, passwordHash, "ROLE_USER", true, true);
        } catch (SQLException e) {
            LOGGER.error("add user sql error ");
        }
        ConnectionManager.closeConnection(c);
    }
}
