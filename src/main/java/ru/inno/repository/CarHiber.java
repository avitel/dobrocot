package ru.inno.repository;

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
        return HibernateSessionFactory.getSessionFactory().openSession().get(Car.class, id);
    }

    @Override
    public int addCar(int owner_id, int mark_id, int model_id, Timestamp assembledate, int engine_id, int numbeerofseats, int color_id, int dayprice) {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Person person = session.get(Person.class, owner_id);
        Mark mark = session.get(Mark.class, mark_id);
        Model model = session.get(Model.class, model_id);
        Engine engine = session.get(Engine.class, engine_id);
        Color color = session.get(Color.class, color_id);

        Transaction tx = session.beginTransaction();
        int id = (int)session.save(new Car(person, mark, model ,assembledate, engine, numbeerofseats, color, dayprice));
        tx.commit();
        session.close();
        return id;
    }

    @Override
    public List<Car> getFilteredCars(QueryBuilder filter) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery(filter.getHQLquery());
        filter.setHQLParameters(query, session);
        List<Car> list = query.list();

        return list;
    }

    @Override
    public List<Car> getCarsByPerson(int person_id) {

        QueryBuilder builder = new QueryBuilder(null, null, null, null, person_id);
        return getFilteredCars(builder);
    }
}
