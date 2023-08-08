package com.example.H2JUnit;

import com.example.Order;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class H2OrderTest {
    private Connection connection;
    private Statement statement;

    @BeforeEach
    public void setUp() throws SQLException {

        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"); // Testowa baza H2 w pamięci
        dataSource.setUser("your_username");
        dataSource.setPassword("your_password");

        // Tworzenie połączenia z bazą danych
        connection = dataSource.getConnection();
    }

    @AfterEach
    public void after() throws SQLException {
        statement.executeUpdate("DROP table zamowienia");
        statement.close();
        connection.close();
    }

    @Test
    void showAllOrderTest() throws SQLException {
        statement = connection.createStatement();

        statement.executeUpdate( "CREATE TABLE zamowienia (id INT AUTO_INCREMENT, id_klienci INT, id_ksiazki INT, data VARCHAR(255), PRIMARY KEY (id))");
        statement.executeUpdate("INSERT INTO zamowienia (id_klienci, id_ksiazki, data) VALUES(1 , 2 , '24072023')");
        statement.executeUpdate("INSERT INTO zamowienia (id_klienci, id_ksiazki, data) VALUES(2 , 3 , '25072023')");

        Order order = new Order(connection);
        order.showAllOrder();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM zamowienia");
        resultSet.absolute(1);
        assertEquals(1, resultSet.getInt(2));
        assertEquals(2, resultSet.getInt(3));
        resultSet.absolute(2);
        assertEquals(2, resultSet.getInt(2));
        assertEquals(3, resultSet.getInt(3));
    }



    @Test
    void showOrderByIdTest() throws SQLException {
        statement = connection.createStatement();

        statement.executeUpdate( "CREATE TABLE zamowienia (id INT AUTO_INCREMENT, id_klienci INT, id_ksiazki INT, data VARCHAR(255), PRIMARY KEY (id))");
        statement.executeUpdate("INSERT INTO zamowienia (id_klienci, id_ksiazki, data) VALUES(1 , 2 , '24072023')");
        statement.executeUpdate("INSERT INTO zamowienia (id_klienci, id_ksiazki, data) VALUES(2 , 3 , '25072023')");

        Order order = new Order(connection);
        order.showOrderById(1);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM zamowienia WHERE id = 1");
        resultSet.absolute(1);
        assertEquals(1, resultSet.getInt(2));
        assertEquals(2, resultSet.getInt(3));
    }
    @Test
    void addOrderTest() throws SQLException {
        statement = connection.createStatement();

        statement.executeUpdate( "CREATE TABLE zamowienia (id INT AUTO_INCREMENT, id_klienci INT, id_ksiazki INT, data VARCHAR(255), PRIMARY KEY (id))");

        Order order = new Order(connection);
        order.addOrder(1,2);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM zamowienia");
        resultSet.absolute(1);
        assertEquals(1, resultSet.getInt(2));
        assertEquals(2, resultSet.getInt(3));
    }
    @Test
    void deleteOrderTest() throws SQLException {
        statement = connection.createStatement();

        statement.executeUpdate( "CREATE TABLE zamowienia (id INT AUTO_INCREMENT, id_klienci INT, id_ksiazki INT, data VARCHAR(255), PRIMARY KEY (id))");
        statement.executeUpdate("INSERT INTO zamowienia (id_klienci, id_ksiazki, data) VALUES(1 , 2 , '24072023')");

        Order order = new Order(connection);
        order.deleteOrder(1);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM zamowienia WHERE id = 1");
        assertEquals(false, resultSet.next());
    }

    @Test
    void updateOrder() throws SQLException {
        statement = connection.createStatement();

        statement.executeUpdate( "CREATE TABLE zamowienia (id INT AUTO_INCREMENT, id_klienci INT, id_ksiazki INT, data VARCHAR(255), PRIMARY KEY (id))");
        statement.executeUpdate("INSERT INTO zamowienia (id_klienci, id_ksiazki, data) VALUES(1 , 2 , '24072023')");
        statement.executeUpdate("INSERT INTO zamowienia (id_klienci, id_ksiazki, data) VALUES(2 , 3 , '25072023')");

        Order order = new Order(connection);
        order.updateOrder(1,2,2);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM zamowienia");
        resultSet.absolute(1);
        assertEquals(2, resultSet.getInt(2));
        assertEquals(2, resultSet.getInt(3));
    }

    }
