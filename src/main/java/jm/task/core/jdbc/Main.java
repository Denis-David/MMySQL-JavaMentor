package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
//import jm.task.core.jdbc.util.Util;

//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.concurrent.Executor;

public class Main {
    public static void main(String[] args) {
        UserDao daoJDBC = new UserDaoHibernateImpl();
        //UserDaoJDBCImpl daoJDBC = new UserDaoJDBCImpl();
        //daoJDBC.createUsersTable();
        daoJDBC.saveUser("David", "Bengur", (byte) 33);
       // daoJDBC.removeUserById(1);
        //daoJDBC.cleanUsersTable();
        //daoJDBC.dropUsersTable();
        //System.out.println(daoJDBC.getAllUsers());

    }
}

