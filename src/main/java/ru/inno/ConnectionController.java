package ru.inno;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Класс синглтон для работы с подключением к БД.
 * Потоконебезопасен.
 */

public class ConnectionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionController.class);
    private Connection conn = null;
    private static ConnectionController instance;
    Properties property = new Properties();

    /**
     * Инициализирует класс.
     *
     * @return Инстанс класса.
     */
    public static ConnectionController createController() {
        if (instance != null) {
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
        LOGGER.debug("Запустился контроллер соединений.");
        return instance;
    }

    /**
     * Устанавливает соединение с БД.
     */
    public void setConnection() {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            FileInputStream fis;
            fis = new FileInputStream("./config.properties");
            property.load(fis);
            String url = property.getProperty("db.url");
            String login = property.getProperty("db.login");
            String pass = property.getProperty("db.pass");
            conn = DriverManager.getConnection(url, login, pass);
            conn.setAutoCommit(false);
            LOGGER.debug("Соединение с БД установлено.");
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("Соединиться с БД не удалось.");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private ConnectionController() {
    }

    /**
     * Возвращает ссылку на соединение.
     *
     * @return Соединение.
     */
    public Connection getConnection() {
        return conn;
    }

    /**
     * Закрывает соединение.
     */
    public void closeConnection() {
        try {
            LOGGER.debug("Соединение с БД остановлено.");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
