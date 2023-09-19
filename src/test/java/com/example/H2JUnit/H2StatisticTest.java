package com.example.H2JUnit;

import com.example.Statistics;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class H2StatisticTest {

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
    void showOrderStatistic() throws SQLException {
        statement = connection.createStatement();

        statement.executeUpdate("CREATE TABLE ksiazki (id INT AUTO_INCREMENT, tytul VARCHAR(255), autor VARCHAR(255), cena INT, ilosc INT, PRIMARY KEY (id))");
        statement.executeUpdate("INSERT INTO ksiazki ( tytul, autor, cena, ilosc) VALUES('Morderstwo' , 'Agata Christie' , 23 , 15)");
        statement.executeUpdate("INSERT INTO ksiazki (tytul, autor, cena, ilosc) VALUES('Harry Poter' , 'J.K.Rowling' , 30 , 16)");
        statement.executeUpdate("INSERT INTO ksiazki (tytul, autor, cena, ilosc) VALUES('Zemsta' , 'Aleksander Fredro' , 45 , 14)");

        statement.executeUpdate( "CREATE TABLE zamowienia (id INT AUTO_INCREMENT, id_klienci INT, id_ksiazki INT, data VARCHAR(255), PRIMARY KEY (id))");
        statement.executeUpdate("INSERT INTO zamowienia (id_klienci, id_ksiazki, data) VALUES(1 , 2 , '24072023')");
        statement.executeUpdate("INSERT INTO zamowienia (id_klienci, id_ksiazki, data) VALUES(2 , 2 , '25072023')");
        statement.executeUpdate("INSERT INTO zamowienia (id_klienci, id_ksiazki, data) VALUES(3 , 1 , '25072023')");

        Statistics statistics = new Statistics(connection);
        statistics.showOrderStatistic();

        ResultSet resultSet = statement.executeQuery("SELECT id_ksiazki, ksiazki.tytul, COUNT(*) AS liczba_zamowionych_ksiazek FROM zamowienia INNER JOIN ksiazki ON ksiazki.id = zamowienia.id_ksiazki GROUP BY id_ksiazki ORDER BY liczba_zamowionych_ksiazek DESC");
        resultSet.absolute(1);
        assertEquals(2, resultSet.getInt(1));
        assertEquals("Harry Poter", resultSet.getString(2));
        assertEquals(2, resultSet.getInt(3));

    }

    @Test
    void showClientStatistic() throws SQLException {
        statement = connection.createStatement();

        statement.executeUpdate("CREATE TABLE klienci (id INT AUTO_INCREMENT, imie VARCHAR(255), nazwisko VARCHAR(255), adres VARCHAR(255), PRIMARY KEY (id))");
        statement.executeUpdate("INSERT INTO klienci (imie, nazwisko, adres) VALUES('Teofil' ,'Wilczyński', 'Rzeszow')");
        statement.executeUpdate("INSERT INTO klienci (imie, nazwisko, adres) VALUES('Wanda' , 'Wyszyńska' , 'Warszawa')");
        statement.executeUpdate("INSERT INTO klienci (imie, nazwisko, adres) VALUES('Izabla' , 'Siarzewska' , 'Łódź')");

        statement.executeUpdate( "CREATE TABLE zamowienia (id INT AUTO_INCREMENT, id_klienci INT, id_ksiazki INT, data VARCHAR(255), PRIMARY KEY (id))");
        statement.executeUpdate("INSERT INTO zamowienia (id_klienci, id_ksiazki, data) VALUES(1 , 2 , '24072023')");
        statement.executeUpdate("INSERT INTO zamowienia (id_klienci, id_ksiazki, data) VALUES(2 , 2 , '25072023')");
        statement.executeUpdate("INSERT INTO zamowienia (id_klienci, id_ksiazki, data) VALUES(2 , 1 , '25072023')");

        Statistics statistics = new Statistics(connection);
        statistics.showClientStatistic();

        ResultSet resultSet = statement.executeQuery("SELECT id_klienci, klienci.imie, klienci.nazwisko, COUNT(*) AS najbardziej_aktywny_klient  FROM zamowienia INNER JOIN klienci ON klienci.id = zamowienia.id_klienci GROUP BY id_klienci ORDER BY najbardziej_aktywny_klient DESC");
        resultSet.absolute(1);
        assertEquals(2, resultSet.getInt(1));
        assertEquals("Wanda", resultSet.getString(2));
        assertEquals("Wyszyńska", resultSet.getString(3));
        assertEquals(2, resultSet.getInt(4));

    }

}
