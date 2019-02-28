package ru.inno.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.inno.dao.ColorDAO;
import ru.inno.entity.Color;
import ru.inno.entity.Person;

import java.util.List;

@Repository
public class ColorHiber implements ColorDAO {

    @Override
    public List<Color> getColors() {
        Query query = HibernateSessionFactory.getSessionFactory().openSession().createQuery("from Color");
        List<Color> list = query.list();
        return list;
    }


    @Override
    public Color getColor(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(Color.class, id);
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
