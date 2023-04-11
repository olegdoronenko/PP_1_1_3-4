package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();
    private Transaction tx;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS Users_Table" +
                    "(id bigint not null auto_increment, age tinyint, last_name varchar(255), name varchar(255), " +
                    "primary key (id))").addEntity(User.class).executeUpdate();
            tx.commit();
        } catch (Exception e) {

        }
    }


    @Override
    public void dropUsersTable () {
        try (Session session = sessionFactory.openSession()) {
            tx =  session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS Users_Table")
                    .executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            tx =  session.beginTransaction();
            session.createNativeQuery("DELETE FROM Users_Table WHERE id =:id")
                    .setParameter("id", id)
                    .executeUpdate();
            tx.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }

        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            tx =  session.beginTransaction();
            users = session.createQuery("FROM User").list();
                    //.executeUpdate();
            //return session.createQuery("FROM User").list();
            //tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            tx =  session.beginTransaction();
            session.createNativeQuery("DELETE FROM Users_Table")
                    .executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
