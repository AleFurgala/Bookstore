package com.example.H2JUnit;


import com.example.Client;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class H2ClientTest {
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
        statement.executeUpdate("DROP table klienci");
        statement.close();
        connection.close();
    }

    @Test
    void showAllClientsTest() throws SQLException {
        statement = connection.createStatement();

        statement.executeUpdate("CREATE TABLE klienci (id INT AUTO_INCREMENT, imie VARCHAR(255), nazwisko VARCHAR(255), adres VARCHAR(255), PRIMARY KEY (id))");
        statement.executeUpdate("INSERT INTO  klienci (imie, nazwisko, adres) VALUES('Jan' ,'Nowak', 'Rzeszow')");
        statement.executeUpdate("INSERT INTO  klienci (imie, nazwisko, adres) VALUES('Krystyna' , 'Kowalska' , 'Warszawa')");

        Client client = new Client(connection);
        client.showAllClients();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM klienci");
        resultSet.absolute(1);
        assertEquals("Jan", resultSet.getString(2));
        assertEquals("Nowak", resultSet.getString(3));
        resultSet.absolute(2);
        assertEquals("Krystyna", resultSet.getString(2));
        assertEquals("Kowalska", resultSet.getString(3));
    }

    @Test
    void showClientByNameOrSurnameTest() throws SQLException{

        statement = connection.createStatement();

        statement.executeUpdate("CREATE TABLE klienci (id INT AUTO_INCREMENT, imie VARCHAR(255), nazwisko VARCHAR(255), adres VARCHAR(255), PRIMARY KEY (id))");
        statement.executeUpdate("INSERT INTO  klienci (imie, nazwisko, adres) VALUES('Jan' ,'Nowak', 'Rzeszow')");
        statement.executeUpdate("INSERT INTO  klienci (imie, nazwisko, adres) VALUES('Krystyna' , 'Kowalska' , 'Warszawa')");

        Client client = new Client(connection);
        client.showClientByNameOrSurname("Jan");

        ResultSet resultSet = statement.executeQuery("SELECT * FROM klienci WHERE imie LIKE '%Jan%' OR nazwisko LIKE '%Jan%'");
        resultSet.absolute(1);
        assertEquals("Jan", resultSet.getString(2));
        assertEquals("Nowak", resultSet.getString(3));
    }

}
