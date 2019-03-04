package ru.inno.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.inno.dao.ModelDAO;
import ru.inno.entity.*;

import java.util.List;

@Repository
public class ModelHiber implements ModelDAO {

    @Override
    public List<Model> getModels(Integer mark_id) {

        List<Model> list;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query query;
            if (mark_id != null) {
                Mark mark = session.get(Mark.class, mark_id);
                query = session.createQuery("from Model where mark = :mark");
                query.setParameter("mark", mark);
            } else {
                query = session.createQuery("from Model");
            }
            list = query.list();
        }
        return list;
    }


    @Override
    public Model getModel(int id) {
        Model model;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            model = session.get(Model.class, id);
        }
        return model;
    }


    @Override
    public int addModel(int id_mark, String name) {
        int id = 0;
        Transaction tx = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Mark mark = session.get(Mark.class, id_mark);

            tx = session.beginTransaction();
            id = (int) session.save(new Model(mark, name));
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        return id;
    }
}
