package ru.raven;

//mport org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс синглтон для работы с подключением к БД.
 * Потоконебезопасен.
 */

public class ConnectionController {
    //private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private Connection conn = null;
    private static ConnectionController instance;

    /**
     * Инициализирует класс.
     * @return Инстанс класса.
     */
    public static ConnectionController createController(){
        if(instance != null){
            //LOGGER.debug("Попытка создать еще один контроллер соединений не увенчалась успехом.");
            return instance;
        }
        try {
            instance = ConnectionController.class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //LOGGER.debug("Запустился контроллер соединений.");
        return instance;
    }

    /**
     * Устанавливает соединение с БД.
     */
    public void setConnection(){
        String url = "jdbc:postgresql://localhost:5433/postgres";
        String login = "postgres";
        String pass = "admin";
        try {
            conn = DriverManager.getConnection(url, login, pass);
            conn.setAutoCommit(false);
            //LOGGER.debug("Соединение с БД установлено.");
        } catch (SQLException e) {
            e.printStackTrace();
            //LOGGER.error("Соединиться с БД не удалось.");
        }
    }

    private ConnectionController(){
    }

    /**
     * Возвращает ссылку на соединение.
     * @return Соединение.
     */
    public Connection getConnection(){
        return conn;
    }

    /**
     * Закрывает соединение.
     */
    public void closeConnection(){
        try {
            //LOGGER.debug("Соединение с БД остановлено.");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
