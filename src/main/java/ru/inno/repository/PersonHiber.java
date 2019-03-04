package ru.inno.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.inno.dao.PersonDAO;
import ru.inno.entity.Person;

import java.util.List;

@Repository
public class PersonHiber implements PersonDAO {

    @Override
    public List<Person> getPersons() {
        List<Person> list;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Person");
            list = query.list();
        }
        return list;
    }


    @Override
    public Person getPerson(int id) {
        Person person;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            person = session.get(Person.class, id);
        }
        return person;
    }


    @Override
    public Person getPerson(String login) {
        List<Person> list;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Person where login = :param");
            query.setParameter("param", login);
            list = query.list();
        }
        if (list.size() == 0){
            return null;
        }
        return list.get(0);
    }


    @Override
    public int addPerson(String name, String login, String pass, String role) {
        int id=0;
        Transaction tx = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            id = (int)session.save(new Person(name, login, pass ,role));
            tx.commit();
        }catch (HibernateException e){
            if (tx != null) {
                tx.rollback();
            }
        }
        return id;
    }
}
