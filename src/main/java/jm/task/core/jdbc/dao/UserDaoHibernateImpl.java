package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS Users_Table" +
                    "(id bigint not null auto_increment, age tinyint, last_name varchar(255), name varchar(255), " +
                    "primary key (id))").executeUpdate();

            session.getTransaction().commit();
            //session.close();

        } catch (HibernateException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS Users_Table")
                    .executeUpdate();
            //session.clear();
        //.addEntity(User.class).executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            User user  = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);

            session.save(user);
            session.getTransaction().commit();


        } catch (HibernateException e) {
            e.printStackTrace();

        }
    }


    @Override
    public void removeUserById(long id) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.createQuery("delete from User where id = " + id)
                    .executeUpdate();

            session.getTransaction().commit();


        } catch (HibernateException e) {
            e.printStackTrace();

        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            return session.createQuery("FROM User").list();


        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.createQuery("delete from User")
                    .executeUpdate();

            session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();

        }
    }
}
