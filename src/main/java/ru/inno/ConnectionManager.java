package ru.inno;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {

    private static InitialContext ic;
    private static DataSource ds = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManager.class);

static{
    setConnection();
}

    private static void setConnection(){
        if (ds==null) {
            try {
                Class.forName("org.postgresql.Driver").newInstance();
                ic = new InitialContext();
                ds = (DataSource) ic.lookup("java:/comp/env/jdbc/postgres");
                LOGGER.debug("Соединение с БД установлено.");
            } catch (Exception e) {
                LOGGER.debug("Подключиться с БД не удалось!");
                LOGGER.error(e.getStackTrace().toString());
            }
        }
        else {
            LOGGER.error("Соединение с базой уже установлено!");
        }

    }

    private ConnectionManager(){};

    public static Connection getConnection(){
        if(ds != null){
            try {
                return ds.getConnection();
            } catch (SQLException e) {
                LOGGER.error(e.getStackTrace().toString());
            }
        }
        else {
            LOGGER.error("Соединение с базой не установлено!");
        }
        return null;
    }

    public static void closeConnection(Connection c)
    {
        try {
            c.close();
            LOGGER.debug("Соединение закрыто.");
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getStackTrace().toString());
            LOGGER.debug("Соединение закрыть не удалось.");
        }
    }
}
