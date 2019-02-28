package ru.inno.repository;

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

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        List<Model> list;
        Query query;

        if (mark_id != null){
            Mark mark = session.get(Mark.class, mark_id);
            query = session.createQuery("from Model where mark = :mark");
            query.setParameter("mark", mark);
        }else{
            query = session.createQuery("from Model");
        }

        list = query.list();
        return list;
    }


    @Override
    public Model getModel(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(Model.class, id);
    }


    @Override
    public int addModel(int id_mark, String name) {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Mark mark = session.get(Mark.class, id_mark);

        Transaction tx = session.beginTransaction();
        int id = (int)session.save(new Model(mark, name));
        tx.commit();
        session.close();
        return id;
    }
}
