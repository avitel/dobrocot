package ru.inno.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.inno.ConnectionController;
import ru.inno.dao.*;

import java.sql.SQLException;



public class RegistrationService {


    private static ConnectionController connectionController = ConnectionController.createController();

    private static final Logger LOGGER = LoggerFactory.getLogger(EngineImpl.class);


    public void addUser(String name, String login, String password, String isseller, String iscustomer){
        System.out.println("service");

        PersonDAO dao = new PersonImpl(connectionController.getConnection());
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordHash = encoder.encode(password);
        try {
            dao.addPerson(name, login, passwordHash, "ROLE_USER", true, true);
        } catch (SQLException e) {
            LOGGER.error("add user sql error ");
        }
    }
}
