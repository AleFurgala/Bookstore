package com.example.H2JUnit;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
}
