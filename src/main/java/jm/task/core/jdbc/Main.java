package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;

//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.concurrent.Executor;

public class Main {
    public static void main(String[] args) {
        UserDao daoJDBC = new UserDaoJDBCImpl();
        daoJDBC.createUsersTable();
        daoJDBC.saveUser("fgg1", "kgdkvdd", (byte) 56);

        daoJDBC.saveUser("fgg2", "kgdkvdd", (byte) 56);

        daoJDBC.saveUser("fgg3", "kgdkvdd", (byte) 56);

        daoJDBC.saveUser("fgg4", "kgdkvdd", (byte) 56);

        daoJDBC.getAllUsers();
        //daoJDBC.removeUserById(10);
        daoJDBC.cleanUsersTable();
        daoJDBC.dropUsersTable();


    }
}
