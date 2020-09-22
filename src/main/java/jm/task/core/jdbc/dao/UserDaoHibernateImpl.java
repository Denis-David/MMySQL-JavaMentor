package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }
    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory("update").openSession()) {
            Transaction tx1 = session.beginTransaction();
            String SQL = "CREATE TABLE IF NOT EXISTS users" +
                    "(id int NOT NULL AUTO_INCREMENT, " +
                    " name VARCHAR(45), " +
                    " lastName VARCHAR (45), " +
                    " age INTEGER not NULL, " +
                    "PRIMARY KEY (id))";
            session.createSQLQuery(SQL).executeUpdate();
            tx1.commit();
            System.out.println("Таблица готова");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        //String SQL2 = " table User";
        try (Session session = Util.getSessionFactory("update").openSession()) {
            Transaction tx1 = session.beginTransaction();
            SQLQuery query = session.createSQLQuery("drop table users");
            query.executeUpdate(); // создание запроса
            tx1.commit();
            System.out.println("Table successfully removed...");
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = Util.getSessionFactory("update").openSession();

            Transaction tx1 = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            tx1.commit();
            session.close();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        User myObject ;
        try (Session session = Util.getSessionFactory("update").openSession()) {
            myObject = (User) session.load(User.class, id);
            session.delete(myObject);
            //This makes the pending delete to be done
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory("update").openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {
        String SQL2 = "delete from User";
        try (Session session = Util.getSessionFactory("update").openSession()) {
            Transaction tx1 = session.beginTransaction();
            Query query = session.createQuery(SQL2);
            query.executeUpdate();
            tx1.commit();
            System.out.println("Table successfully removed...");
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}
