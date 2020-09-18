package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
//import jm.task.core.jdbc.util.Util;

//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.concurrent.Executor;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl daoJDBC = new UserDaoJDBCImpl();
        daoJDBC.createUsersTable();
        daoJDBC.saveUser("fgg1", "kgdkvdd", (byte) 56);
        //daoJDBC.removeUserById(10);
        //daoJDBC.cleanUsersTable();
        //daoJDBC.dropUsersTable();
        daoJDBC.getAllUsers();

    }
}
