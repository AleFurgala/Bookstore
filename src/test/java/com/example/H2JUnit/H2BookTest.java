package com.example.H2JUnit;

import com.example.Book;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class H2BookTest {
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
        statement.executeUpdate("DROP table ksiazki");
        statement.close();
        connection.close();
    }

    @Test
    void showAllBooksTest() throws SQLException {
        statement = connection.createStatement();

        statement.executeUpdate("CREATE TABLE ksiazki (id INT AUTO_INCREMENT, tytul VARCHAR(255), autor VARCHAR(255), cena INT, ilosc INT, PRIMARY KEY (id))");

        statement.executeUpdate("INSERT INTO ksiazki (tytul, autor, cena, ilosc) VALUES('Water' , 'Paula Hawkins' , 23 , 2)");
        statement.executeUpdate("INSERT INTO ksiazki (tytul, autor, cena, ilosc) VALUES('xyz' , 'abc' , 30 , 3)");

        Book book = new Book(connection);
        book.showAllBooks();

        //com.example.ResultSetSpy resultSetSpy = new com.example.ResultSetSpy(connection.createStatement().executeQuery("SELECT * FROM ksiazki"));
        //assertEquals(2, resultSetSpy.getCount()); // Oczekujemy 2 rekordów
        //assertEquals("Water", resultSetSpy.getRow(1).getString("tytul"));

        ResultSet resultSet = statement.executeQuery("SELECT * FROM ksiazki");
        resultSet.absolute(1);
        assertEquals("Water", resultSet.getString(2));
        assertEquals("Paula Hawkins", resultSet.getString(3));
        resultSet.absolute(2);
        assertEquals("xyz", resultSet.getString(2));
        assertEquals("abc", resultSet.getString(3));
    } @Test
    void showAvailableBooksTest() throws SQLException {
        statement = connection.createStatement();

        statement.executeUpdate("CREATE TABLE ksiazki (id INT AUTO_INCREMENT, tytul VARCHAR(255), autor VARCHAR(255), cena INT, ilosc INT, PRIMARY KEY (id))");

        statement.executeUpdate("INSERT INTO ksiazki (tytul, autor, cena, ilosc) VALUES('Water' , 'Paula Hawkins' , 23 , 0)");
        statement.executeUpdate("INSERT INTO ksiazki (tytul, autor, cena, ilosc) VALUES('xyz' , 'abc' , 30 , 3)");

        Book book = new Book(connection);
        book.showAvailableBooks();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM ksiazki WHERE ilosc > 0");
        resultSet.absolute(1);
        assertEquals("xyz", resultSet.getString(2));
        assertEquals("abc", resultSet.getString(3));

    }

    @Test
    void showBooksByTitleOrAuthor() throws SQLException {
        statement = connection.createStatement();

        statement.executeUpdate("CREATE TABLE ksiazki (id INT AUTO_INCREMENT, tytul VARCHAR(255), autor VARCHAR(255), cena INT, ilosc INT, PRIMARY KEY (id))");

        statement.executeUpdate("INSERT INTO ksiazki (tytul, autor, cena, ilosc) VALUES('Water' , 'Paula Hawkins' , 23 , 2)");
        statement.executeUpdate("INSERT INTO ksiazki (tytul, autor, cena, ilosc) VALUES('xyz' , 'abc' , 30 , 3)");

        Book book = new Book(connection);
        book.showBooksByTitleOrAuthor("Water");

        ResultSet resultSet = statement.executeQuery("SELECT * FROM ksiazki WHERE tytul LIKE '%Water%' OR autor LIKE '%Water%'");
        resultSet.absolute(1);
        assertEquals("Water", resultSet.getString(2));
        assertEquals("Paula Hawkins", resultSet.getString(3));
    }

    @Test
    void addBookTest() throws SQLException {
        statement = connection.createStatement();

        statement.executeUpdate("CREATE TABLE ksiazki (id INT AUTO_INCREMENT, tytul VARCHAR(255), autor VARCHAR(255), cena INT, ilosc INT,id_admin LONG, PRIMARY KEY (id))");

        Book book = new Book(connection);
        book.addBook("abc", "xyz", 2, 1, 1L);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ksiazki");
        resultSet.absolute(1);
        assertEquals("abc", resultSet.getString(2));
        assertEquals("xyz", resultSet.getString(3));
    }

    @Test
    void deleteBookTest() throws SQLException {
        statement = connection.createStatement();

        statement.executeUpdate("CREATE TABLE ksiazki (id INT AUTO_INCREMENT, tytul VARCHAR(255), autor VARCHAR(255), cena INT, ilosc INT, PRIMARY KEY (id))");

        statement.executeUpdate("INSERT INTO ksiazki (tytul, autor, cena, ilosc) VALUES('Water' , 'Paula Hawkins' , 23 , 2)");
        statement.executeUpdate("INSERT INTO ksiazki (tytul, autor, cena, ilosc) VALUES('xyz' , 'abc' , 30 , 3)");

        Book book = new Book(connection);
        book.deleteBook(1L);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM ksiazki WHERE id = 1");
        assertEquals(false, resultSet.next());
    }

    @Test
    void updateBookTest() throws SQLException {
        statement = connection.createStatement();

        statement.executeUpdate("CREATE TABLE ksiazki (id INT AUTO_INCREMENT, tytul VARCHAR(255), autor VARCHAR(255), cena INT, ilosc INT, PRIMARY KEY (id))");
        statement.executeUpdate("INSERT INTO ksiazki (tytul, autor, cena, ilosc) VALUES('Water' , 'Paula Hawkins' , 23 , 2)");

        Book book = new Book(connection);
        book.updateBook(1L, "zz", "yy", 20, 3);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM ksiazki");
        resultSet.absolute(1);
        assertEquals("zz", resultSet.getString(2));
        assertEquals("yy", resultSet.getString(3));
    }

    @Test
    void deleteAmountTest() throws SQLException {
        statement = connection.createStatement();

        statement.executeUpdate("CREATE TABLE ksiazki (id INT AUTO_INCREMENT, tytul VARCHAR(255), autor VARCHAR(255), cena INT, ilosc INT, PRIMARY KEY (id))");
        statement.executeUpdate("INSERT INTO ksiazki (tytul, autor, cena, ilosc) VALUES('Water' , 'Paula Hawkins' , 23 , 3)");
        statement.executeUpdate("INSERT INTO ksiazki (tytul, autor, cena, ilosc) VALUES('xyz' , 'abc' , 23 , 0)");

        Book book = new Book(connection);
        book.deleteAmount(1L);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM ksiazki");
        resultSet.absolute(1);
        assertEquals(2, resultSet.getInt(5));
        resultSet.absolute(2);
        assertEquals(0, resultSet.getInt(5));
    }
}
