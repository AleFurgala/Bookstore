import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class H2BookTest {
    private Connection connection;

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
        connection.close();

    }
    @Test
    void showAllBooksTest() throws SQLException {

        Statement statement = connection.createStatement();

        // Utworzenie tabeli "users"
        statement.executeUpdate("CREATE TABLE ksiazki (id INT AUTO_INCREMENT, tytul VARCHAR(255), autor VARCHAR(255), cena INT, ilosc INT, PRIMARY KEY (id))");

        // Wstawienie danych testowych
        statement.executeUpdate("INSERT INTO ksiazki (tytul, autor, cena, ilosc) VALUES('Water' , 'Paula Hawkins' , 23 , 2)");
        statement.executeUpdate("INSERT INTO ksiazki (tytul, autor, cena, ilosc) VALUES('xyz' , 'abc' , 30 , 3)");

        statement.close();
        Book book = new Book(connection);
        book.showAllBooks();


        // Assert
        // Dla testów jednostkowych możemy skupić się na przechwyceniu wyników i ich analizie
        // zamiast sprawdzania wydruków na konsoli. W tym przykładzie użyjemy ResultSetSpy, który pozwala
        // na przechwycenie wyników zapytania i ich analizę.

        ResultSetSpy resultSetSpy = new ResultSetSpy(connection.createStatement().executeQuery("SELECT * FROM ksiazki"));
        //assertEquals(2, resultSetSpy.getCount()); // Oczekujemy 2 rekordów
        assertEquals("Water", resultSetSpy.getRow(1).getString("tytul"));
        assertEquals("Paula Hawkins", resultSetSpy.getRow(1).getString("autor"));
        assertEquals("xyz", resultSetSpy.getRow(2).getString("tytul"));
        assertEquals("abc", resultSetSpy.getRow(2).getString("autor"));

    }

    @Test
    void deleteBookTest () throws SQLException {
        Statement statement = connection.createStatement();

        // Utworzenie tabeli "users"
        statement.executeUpdate("CREATE TABLE ksiazki (id INT AUTO_INCREMENT, tytul VARCHAR(255), autor VARCHAR(255), cena INT, ilosc INT, PRIMARY KEY (id))");

        // Wstawienie danych testowych
        statement.executeUpdate("INSERT INTO ksiazki (tytul, autor, cena, ilosc) VALUES('Water' , 'Paula Hawkins' , 23 , 2)");
        statement.executeUpdate("INSERT INTO ksiazki (tytul, autor, cena, ilosc) VALUES('xyz' , 'abc' , 30 , 3)");

        statement.close();
        Book book = new Book(connection);

         int bookToDelete = 1;
        book.deleteBook(bookToDelete);
        //ResultSetSpy resultSetSpy = new ResultSetSpy(connection.createStatement().executeQuery("DELETE FROM ksiazki WHERE id = '" + bookToDelete + "'"));
        ResultSetSpy resultSetSpy2 = new ResultSetSpy(connection.createStatement().executeQuery("SELECT * FROM ksiazki"));
        assertEquals("xyz", resultSetSpy2.getRow(1).getString("tytul"));



    }
}
