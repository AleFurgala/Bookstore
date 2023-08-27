package com.example.H2JUnit;

import com.example.AdminAccounts;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class H2AdminAccountsTest {
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
        statement.executeUpdate("DROP table konta_administratorow");
        statement.close();
        connection.close();
    }

    @Test
    void getPasswordBasedLoginTest() throws SQLException {
        statement = connection.createStatement();

        statement.executeUpdate("CREATE TABLE konta_administratorow (id INT AUTO_INCREMENT, login VARCHAR(255), haslo VARCHAR(255), nazwa_uzytkownika VARCHAR(255), PRIMARY KEY (id))");

        statement.executeUpdate("INSERT INTO konta_administratorow (login, haslo, nazwa_uzytkownika) VALUES('admin','ddd','Jurek')");

        AdminAccounts adminAccounts = new AdminAccounts(connection);
        adminAccounts.getPasswordBasedLogin("admin");
        ResultSet resultSet = statement.executeQuery("SELECT * FROM konta_administratorow WHERE login = 'admin'");
        resultSet.absolute(1);
        assertEquals("ddd", resultSet.getString(3));

    }

    @Test
    void getNameBasedLoginTest() throws SQLException {
        statement = connection.createStatement();

        statement.executeUpdate("CREATE TABLE konta_administratorow (id INT AUTO_INCREMENT, login VARCHAR(255), haslo VARCHAR(255), nazwa_uzytkownika VARCHAR(255), PRIMARY KEY (id))");

        statement.executeUpdate("INSERT INTO konta_administratorow (login, haslo, nazwa_uzytkownika) VALUES('admin','ddd','Jurek')");

        AdminAccounts adminAccounts = new AdminAccounts(connection);
        adminAccounts.getNameBasedLogin("admin");
        ResultSet resultSet = statement.executeQuery("SELECT * FROM konta_administratorow WHERE login = 'admin'");
        resultSet.absolute(1);
        assertEquals("Jurek", resultSet.getString(4));
    }

    @Test
    void addAdminAccountTest() throws SQLException {
        statement = connection.createStatement();

        statement.executeUpdate("CREATE TABLE konta_administratorow (id INT AUTO_INCREMENT, login VARCHAR(255), haslo VARCHAR(255), nazwa_uzytkownika VARCHAR(255), PRIMARY KEY (id))");

        AdminAccounts adminAccounts = new AdminAccounts(connection);
        adminAccounts.addAdminAccount("admin", "ddd", "Jurek");
        ResultSet resultSet = statement.executeQuery("SELECT * FROM konta_administratorow ");
        resultSet.absolute(1);
        assertEquals("lDHFY6qkS4Oi1WNNmw002A==", resultSet.getString(3));
        assertEquals("Jurek", resultSet.getString(4));
    }

    @Test
    void checkAccountTypeForAdmin() throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE konta_administratorow (id INT AUTO_INCREMENT, login VARCHAR(255), haslo VARCHAR(255), nazwa_uzytkownika VARCHAR(255), rodzaj_konta VARCHAR(255), PRIMARY KEY (id))");

        statement.executeUpdate("INSERT INTO konta_administratorow (login, haslo, nazwa_uzytkownika, rodzaj_konta) VALUES('admin1','ddd','Jurek', 'admin')");
        statement.executeUpdate("INSERT INTO konta_administratorow (login, haslo, nazwa_uzytkownika, rodzaj_konta) VALUES('admin2','ddd','Dona', 'user')");

        AdminAccounts adminAccounts = new AdminAccounts(connection);
        adminAccounts.checkAccountType(1L);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM konta_administratorow WHERE id = 1");
        resultSet.absolute(1);
        assertEquals("admin", resultSet.getString(5));
        assertEquals(false, adminAccounts.checkAccountType(1L));
    }

    @Test
    void checkAccountTypeForUser() throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE konta_administratorow (id INT AUTO_INCREMENT, login VARCHAR(255), haslo VARCHAR(255), nazwa_uzytkownika VARCHAR(255), rodzaj_konta VARCHAR(255), PRIMARY KEY (id))");

        statement.executeUpdate("INSERT INTO konta_administratorow (login, haslo, nazwa_uzytkownika, rodzaj_konta) VALUES('admin1','ddd','Jurek', 'admin')");
        statement.executeUpdate("INSERT INTO konta_administratorow (login, haslo, nazwa_uzytkownika, rodzaj_konta) VALUES('admin2','ddd','Dona', 'user')");

        AdminAccounts adminAccounts = new AdminAccounts(connection);
        adminAccounts.checkAccountType(2L);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM konta_administratorow WHERE id = 2");
        resultSet.absolute(1);
        assertEquals("user", resultSet.getString(5));
        assertEquals(true, adminAccounts.checkAccountType(2L));
    }


    @Test
    void deleteAdminAccountTest() throws SQLException {
        statement = connection.createStatement();

        statement.executeUpdate("CREATE TABLE konta_administratorow (id INT AUTO_INCREMENT, login VARCHAR(255), haslo VARCHAR(255), nazwa_uzytkownika VARCHAR(255), PRIMARY KEY (id))");

        statement.executeUpdate("INSERT INTO konta_administratorow (login, haslo, nazwa_uzytkownika) VALUES('admin','ddd','Jurek')");

        AdminAccounts adminAccounts = new AdminAccounts(connection);
        adminAccounts.deleteAdminAccount(1L);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM konta_administratorow ");
        assertEquals(false, resultSet.next());
    }
}
