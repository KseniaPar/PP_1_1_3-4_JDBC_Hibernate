package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "firstName VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, age TINYINT NOT NULL)";
            session.createNativeQuery(sql, User.class).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String sql = "DROP TABLE IF EXISTS users";
            session.createNativeQuery(sql, User.class).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            User user = new User(name, lastName, age);
            session.persist(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            User user = session.get(User.class, id);
            if(user != null){
                session.remove(user);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> list = new ArrayList<>();

        try (Session session = Util.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            list = session.createNativeQuery("SELECT * FROM users",User.class).getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return list;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.createNativeQuery("TRUNCATE users",User.class).executeUpdate();


            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
