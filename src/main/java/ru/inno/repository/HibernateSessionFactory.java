package ru.inno.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.entity.*;

public class HibernateSessionFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateSessionFactory.class);

    private static SessionFactory sessionFactory;

    private HibernateSessionFactory() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                long begin = System.currentTimeMillis();

                Configuration configuration = new Configuration();

                configuration.addAnnotatedClass(Person.class);
                configuration.addAnnotatedClass(Car.class);
                configuration.addAnnotatedClass(Color.class);
                configuration.addAnnotatedClass(Mark.class);
                configuration.addAnnotatedClass(Engine.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

                System.out.println("init time : "+(System.currentTimeMillis()-begin));

            } catch (Exception e) {
                LOGGER.error("hibernate factory error",e);
            }
        }
        return sessionFactory;
    }
}
