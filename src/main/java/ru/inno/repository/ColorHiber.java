package ru.inno.repository;

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
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("from Color");
        List<Color> list = query.list();
        session.close();
        return list;
    }


    @Override
    public Color getColor(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Color color = session.get(Color.class, id);
        session.close();
        return color;
    }


    @Override
    public int addColor(String name) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        int id = (int)session.save(new Color(name));
        tx.commit();
        session.close();
        return id;
    }
}
