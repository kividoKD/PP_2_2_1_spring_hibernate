package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public void add(User user, Car car) {
      sessionFactory.getCurrentSession().save(user);
      sessionFactory.getCurrentSession().save(car);
      user.setCar(car);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User whoDrivesThisCar(String model, int series) {
      String hql = "FROM User user LEFT OUTER JOIN FETCH user.car car WHERE car.model = :model and car.series = :series";

      return (User) sessionFactory.getCurrentSession().createQuery(hql)
              .setParameter("model", model)
              .setParameter("series", series)
              .uniqueResult();
   }
}
