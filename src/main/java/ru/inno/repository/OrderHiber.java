package ru.inno.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mockito.internal.matchers.Or;
import org.springframework.stereotype.Repository;
import ru.inno.dao.OrderDAO;
import ru.inno.entity.Car;
import ru.inno.entity.Order;
import ru.inno.entity.Person;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class OrderHiber implements OrderDAO {

    @Override
    public List<Order> getOrders() {
        List<Order> list;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
        list = session.createQuery("from Order").list();
        };
        return list;
    }


    @Override
    public Order getOrder(int id) {
        Order order;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
        order = session.get(Order.class, id);
        };
       return order;
    }


    @Override
    public int addOrder(int id_car, int id_owner, int id_customer, Timestamp dateOrder, Timestamp date_begin, Timestamp date_end, int price) {
        int id=0;
        Transaction tx = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Car car = session.get(Car.class,id_car);
            Person owner = session.get(Person.class, id_owner);
            Person customer = session.get(Person.class, id_customer);
            id = (int)session.save(new Order(car, owner,customer,dateOrder,date_begin,date_end,price));
            tx.commit();
        }catch (HibernateException e){
            if (tx != null) {
                tx.rollback();
            }
        }
        return id;
    }


    @Override
    public List<Order> getOrdersByCustomer(int person_id) {
        List<Order> list;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Person customer = session.get(Person.class, person_id);
            Query query = session.createQuery("from Order where customer = :customer");
            query.setParameter("customer",customer);
            list = query.list();
        };
        return list;
    }


    @Override
    public List<Order> getOrdersBySeller(int person_id) {
        List<Order> list;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
        Person owner = session.get(Person.class, person_id);
        Query query = session.createQuery("from Order where owner = :owner");
        query.setParameter("owner",owner);
        list = query.list();
        };
        return list;
    }

    @Override
    public List<Order> getOrdersByCar(int car_id) {
        List<Order> list;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Car car = session.get(Car.class, car_id);
            Query query = session.createQuery("from Order where car = :car");
            query.setParameter("car",car);
            list = query.list();
        };
        return list;
    }
  
    @Override
    public List<Order> getOrdersByCarPresent(int car_id) {
        List<Order> list;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Car car = session.get(Car.class, car_id);
            Query query = session.createQuery("from Order where car = :car and date_trunc('day',localtimestamp) <= enddate");
            query.setParameter("car",car);
            list = query.list();
        };
        return list;
    }
}
