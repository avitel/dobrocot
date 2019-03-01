package ru.inno.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.inno.dao.EngineDAO;
import ru.inno.entity.Engine;

import java.util.List;

@Repository
public class EngineHiber implements EngineDAO {


    @Override
    public List<Engine> getEngines() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("from Engine");
        List<Engine> list = query.list();
        session.close();
        return list;
    }

    @Override
    public Engine getEngine(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Engine engine = session.get(Engine.class, id);
        session.close();
        return engine;
    }

    @Override
    public int addEngine(String name) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        int id = (int) session.save(new Engine(name));
        tx.commit();
        session.close();
        return id;
    }
}
