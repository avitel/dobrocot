package ru.inno.repository;

import org.hibernate.HibernateException;
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
        List<Engine> list;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Engine");
            list = query.list();
        }
        return list;
    }


    @Override
    public Engine getEngine(int id) {
        Engine engine;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            engine = session.get(Engine.class, id);
        }
        return engine;
    }


    @Override
    public int addEngine(String name) {
        int id=0;
        Transaction tx = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            id = (int) session.save(new Engine(name));
            tx.commit();
        }catch (HibernateException e){
            if (tx != null) {
                tx.rollback();
            }
        }
        return id;
    }
}
