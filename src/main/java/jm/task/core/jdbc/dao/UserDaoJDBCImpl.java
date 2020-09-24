package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private PreparedStatement preparedStatement = null;
    private Statement statement = null;
    private final Util connect = new Util();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            statement = connect.getConnection().createStatement();
            System.out.println("Creating table in selected database...");
            String sql = "CREATE TABLE IF NOT EXISTS users" +
                    "(id int NOT NULL AUTO_INCREMENT, " +
                    " name VARCHAR(45), " +
                    " lastName VARCHAR (45), " +
                    " age INTEGER not NULL, " +
                    "PRIMARY KEY (id))";

            statement.executeUpdate(sql);
            System.out.println("Table successfully created...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql2 = "DROP TABLE IF EXISTS users";
        try {
            statement = connect.getConnection().createStatement();
            System.out.println("Removing table in selected database...");
            statement.executeUpdate(sql2);
            System.out.println("Table successfully removed...");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        user.setName(name);
        String add = "INSERT INTO users (name, lastName, age) VALUES (?,?,?)";
        try (Statement statement = connect.getConnection().createStatement()) {
            preparedStatement = connect.getConnection().prepareStatement(add);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем –" + user.getName() + "добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException s) {
            s.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql3 = "DELETE FROM users WHERE id = ?";
        System.out.println("Removing record with id ");
        try (Statement statement = connect.getConnection().createStatement()) {
            preparedStatement = connect.getConnection().prepareStatement(sql3);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        String query = "select * from users";

        try (Statement statement = connect.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                userList.add(user);
                System.out.println(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Statement statement = connect.getConnection().createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users"); //DELETE  FROM
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
