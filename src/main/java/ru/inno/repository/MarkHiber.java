package ru.inno.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.inno.dao.MarkDAO;
import ru.inno.entity.Car;
import ru.inno.entity.Color;
import ru.inno.entity.Mark;

import java.util.List;

@Repository
public class MarkHiber implements MarkDAO {

    @Override
    public List<Mark> getMarks() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("from Mark");
        List<Mark> list = query.list();
        session.close();
        return list;
    }

    @Override
    public Mark getMark(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Mark mark = session.get(Mark.class, id);
        session.close();
        return mark;
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
