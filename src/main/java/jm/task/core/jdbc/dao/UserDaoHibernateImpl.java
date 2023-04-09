package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();
    Transaction tx;
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
            if (tx != null) {
                tx.rollback();
            }

        }

    }



        @Override
        public void dropUsersTable () {
            try {
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                session.createNativeQuery("DROP TABLE IF EXISTS Users_Table")
                        .executeUpdate();


            } catch (HibernateException e) {
                e.printStackTrace();

            }
        }


    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
