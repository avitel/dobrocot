package ru.inno.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.inno.dao.MarkDAO;
import ru.inno.entity.Car;
import ru.inno.entity.Color;
import ru.inno.entity.Mark;

import java.util.List;

public class MarkHiber implements MarkDAO {

    @Override
    public List<Mark> getMarks() {
        Query query = HibernateSessionFactory.getSessionFactory().openSession().createQuery("from Mark");
        List<Mark> list = query.list();
        return list;
    }

    @Override
    public Mark getMark(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(Mark.class, id);
    }

    @Override
    public int addMark(String name) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        int id = (int)session.save(new Mark(name));
        tx.commit();
        session.close();
        return id;
    }
}
