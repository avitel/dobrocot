package ru.inno.repository;

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
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("from Person");
        List<Person> list = query.list();
        session.close();
        return list;
    }

    @Override
    public Person getPerson(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Person person = session.get(Person.class, id);
        session.close();
        return person;
    }

    @Override
    public Person getPerson(String login) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("from Person where login = :param");
        query.setParameter("param", login);
        List<Person> list = query.list();
        session.close();
        if (list.size() == 0){
            return null;
        }
        return list.get(0);
    }

    @Override
    public int addPerson(String name, String login, String pass, String role) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        int id = (int)session.save(new Person(name, login, pass ,role));
        tx.commit();
        session.close();
        return id;
    }
}
