package test;

import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminAccountsTest {
    private static final String url = "jdbc:mysql://localhost:3306/ksiegarnia";
    private static final String user = "root";
    private static final String password = "";

    private Connection connection;

    @Test
    void getPasswordBasedLogin() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Połączono z bazą danych");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {

            Statement stmt = connection.createStatement();
            String query = "CREATE TABLE test_konta_administratorow (id INT AUTO_INCREMENT, login VARCHAR(255), haslo VARCHAR(255), nazwa_uzytkownika VARCHAR(255), PRIMARY KEY (id))";
            stmt.execute(query);

            String query2 = "INSERT INTO test_konta_administratorow (login, haslo, nazwa_uzytkownika) VALUES('admin','ddd','Jurek'), ('admin2' ,'aaa','Krysia')";
            stmt.execute(query2);

            String query3 = "SELECT haslo FROM test_konta_administratorow WHERE login ='admin'";
            ResultSet rs = stmt.executeQuery(query3);
            String output = "";
            while (rs.next()) {
                output =  rs.getString(1);
            }

            String expectedOutput = "ddd";
            assertEquals(expectedOutput, output);

            String query4 = "DROP table test_konta_administratorow";
            stmt.execute(query4);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    @Test
    void getNameBasedLogin() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Połączono z bazą danych");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {

            Statement stmt = connection.createStatement();
            String query = "CREATE TABLE test_konta_administratorow (id INT AUTO_INCREMENT, login VARCHAR(255), haslo VARCHAR(255), nazwa_uzytkownika VARCHAR(255), PRIMARY KEY (id))";
            stmt.execute(query);

            String query2 = "INSERT INTO test_konta_administratorow (login, haslo, nazwa_uzytkownika) VALUES('admin','ddd','Jurek'), ('admin2' ,'aaa','Krysia')";
            stmt.execute(query2);

            String query3 = "SELECT nazwa_uzytkownika FROM test_konta_administratorow WHERE login ='admin'";
            ResultSet rs = stmt.executeQuery(query3);
            String output = "";
            while (rs.next()) {
                output =  rs.getString(1);
            }

            String expectedOutput = "Jurek";
            assertEquals(expectedOutput, output);

            String query4 = "DROP table test_konta_administratorow";
            stmt.execute(query4);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void addAdminAccount() {
    }

    @Test
    void deleteAdminAccount() {
    }

    @Test
    void dataEncryption() {
    }

    @Test
    void dataDecryption() {
    }
}
