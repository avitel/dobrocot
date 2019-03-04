package ru.inno.repository;

import org.hibernate.HibernateException;
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
        List<Mark> list;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Mark");
            list = query.list();
        }
        return list;
    }


    @Override
    public Mark getMark(int id) {
        Mark mark;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            mark = session.get(Mark.class, id);
        }
        return mark;
    }


    @Override
    public int addMark(String name) {
        int id=0;
        Transaction tx = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            id = (int)session.save(new Mark(name));
            tx.commit();
        }catch (HibernateException e){
            if (tx != null) {
                tx.rollback();
            }
        }
        return id;
    }
}
