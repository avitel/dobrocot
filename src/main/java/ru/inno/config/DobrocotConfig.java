package ru.inno.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.inno.ConnectionManager;
import ru.inno.dao.*;

import java.sql.Connection;

@Configuration
public class DobrocotConfig {

    @Bean
    Connection connection() {
        return ConnectionManager.getConnection();
    }

    @Bean
    CarDAO carDAO(Connection connection) {
        return new CarImpl(connection);
    }

    @Bean
    MarkDAO markDAO(Connection connection) {
        return new MarkImpl(connection);
    }

    @Bean
    ModelDAO modelDAO(Connection connection) {
        return new ModelImpl(connection);
    }

    @Bean
    ColorDAO colorDAO(Connection connection) {
        return new ColorImpl(connection);
    }

    @Bean
    EngineDAO engineDAO(Connection connection) {
        return new EngineImpl(connection);
    }

    @Bean
    PersonDAO personDAO(Connection connection) {
        return new PersonImpl(connection);
    }


}
