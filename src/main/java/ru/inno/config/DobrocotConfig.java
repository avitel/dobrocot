package ru.inno.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.inno.ConnectionManager;
import ru.inno.dao.*;
import ru.inno.repository.PersonHiber;

import java.sql.Connection;

@Configuration
public class DobrocotConfig {

    @Bean
    Connection connection() {
        return ConnectionManager.getConnection();
    }

    @Bean
    EngineDAO engineDAO(Connection connection) {
        return new EngineImpl(connection);
    }

}
