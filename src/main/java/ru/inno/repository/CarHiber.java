package ru.inno.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.inno.dao.CarDAO;
import ru.inno.dao.QueryBuilder;
import ru.inno.entity.*;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class CarHiber implements CarDAO {

    @Override
    public Car getCar(int id) {
        Car car;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            car = session.get(Car.class, id);
        }
        return car;
    }


    @Override
    public int addCar(int owner_id, int mark_id, int model_id, Timestamp assembledate, int engine_id, int numbeerofseats, int color_id, int dayprice) {

        int id=0;
        Transaction tx = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Person person = session.get(Person.class, owner_id);
            Mark mark = session.get(Mark.class, mark_id);
            Model model = session.get(Model.class, model_id);
            Engine engine = session.get(Engine.class, engine_id);
            Color color = session.get(Color.class, color_id);

            tx = session.beginTransaction();
            id = (int) session.save(new Car(person, mark, model, assembledate, engine, numbeerofseats, color, dayprice));
            tx.commit();
        }catch (HibernateException e){
            if (tx != null) {
                tx.rollback();
            }
        }
        return id;
    }


    @Override
    public List<Car> getFilteredCars(QueryBuilder filter) {
        List<Car> list;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery(filter.getHQLquery());
            filter.setHQLParameters(query, session);
            list = query.list();
        };
        return list;
    }

    @Override
    public List<Car> getCarsByPerson(int person_id) {

        QueryBuilder builder = new QueryBuilder(null, null, null, null, person_id);
        return getFilteredCars(builder);
    }

    @Override
    public boolean deleteCar(int id) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            Car car = session.get(Car.class, id);
            car.setIsdeleted(true);
            session.update(car);
            tx.commit();
        }catch (HibernateException e){
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
        return true;
    }
}
