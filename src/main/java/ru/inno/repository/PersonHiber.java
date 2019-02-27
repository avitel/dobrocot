package ru.inno.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.inno.dao.PersonDAO;
import ru.inno.entity.Person;

import java.util.List;

public class PersonHiber implements PersonDAO {
    @Override
    public List<Person> getPersons() {
        return null;
    }

    @Override
    public Person getPerson(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(Person.class, id);
    }

    @Override
    public Person getPerson(String login) {
        Query query = HibernateSessionFactory.getSessionFactory().openSession().createQuery("from Person where login = :param");
        query.setParameter("param", login);
        List<Person> list = query.list();

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
