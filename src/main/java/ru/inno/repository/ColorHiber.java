package ru.inno.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.inno.dao.ColorDAO;
import ru.inno.entity.Color;

import java.util.List;

@Repository
public class ColorHiber implements ColorDAO {

    @Override
    public List<Color> getColors() {
        List<Color> list;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Color");
            list = query.list();
        }
        return list;
    }


    @Override
    public Color getColor(int id) {
        Color color;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            color = session.get(Color.class, id);
        }
        return color;
    }


    @Override
    public int addColor(String name) {
        int id=0;
        Transaction tx = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            id = (int) session.save(new Color(name));
            tx.commit();
        }catch (HibernateException e){
            if (tx != null) {
                tx.rollback();
            }
        }
        return id;
    }
}
