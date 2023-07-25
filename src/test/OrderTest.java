package test;

import main.Order;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

    private static final String url = "jdbc:mysql://localhost:3306/ksiegarnia";
    private static final String user = "root";
    private static final String password = "";

    private Connection connection;


    @Test
    void getDate() {
        Order order = new Order();

        String result = order.getDate();
        Calendar calendar = Calendar.getInstance();
        int expectedYear = calendar.get(Calendar.YEAR);
        int expectedMonth = calendar.get(Calendar.MONTH) + 1;
        int expectedDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String expectedDate = expectedDayOfMonth + "" + expectedMonth + "" + expectedYear;
        assertEquals(expectedDate, result);

    }

    @Test
    void showAllOrder() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Połączono z bazą danych");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {

            Statement stmt = connection.createStatement();
            String query = "CREATE TABLE test_zamowienia (id INT AUTO_INCREMENT, id_klienci INT, id_ksiazki INT, data VARCHAR(255), PRIMARY KEY (id))";
            stmt.execute(query);

            String query2 = "INSERT INTO test_zamowienia (id_klienci, id_ksiazki, data) VALUES(1 , 2 , '24072023'), (2 , 3 , '25072023')";
            stmt.execute(query2);

            String query3 = "SELECT * FROM test_zamowienia";

            ResultSet rs = stmt.executeQuery(query3);
            String output = "";
            while (rs.next()) {
                output = output + rs.getInt(1) + "  " + rs.getInt(2) + "  " + rs.getInt(3) + "  " + rs.getString(4);
            }

            String expectedOutput = "1  1  2  24072023" +
                    "2  2  3  25072023";
            assertEquals(expectedOutput, output);

            String query4 = "DROP table test_zamowienia";
            stmt.execute(query4);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    @Test
    void showOrderById() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Połączono z bazą danych");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {

            Statement stmt = connection.createStatement();
            String query = "CREATE TABLE test_zamowienia (id INT AUTO_INCREMENT, id_klienci INT, id_ksiazki INT, data VARCHAR(255), PRIMARY KEY (id))";
            stmt.execute(query);

            String query2 = "INSERT INTO test_zamowienia (id_klienci, id_ksiazki, data) VALUES(1 , 2 , '24072023'), (2 , 3 , '25072023')";
            stmt.execute(query2);

            String query3 = "SELECT * FROM test_zamowienia WHERE id = 1";
            ResultSet rs = stmt.executeQuery(query3);
            String output = "";
            while (rs.next()) {
                output = rs.getInt(1) + "  " + rs.getInt(2) + "  " + rs.getInt(3) + "  " + rs.getString(4);
            }

            String expectedOutput = "1  1  2  24072023";
            assertEquals(expectedOutput, output);

            String query4 = "DROP table test_zamowienia";
            stmt.execute(query4);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void addOrder() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Połączono z bazą danych");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {

            Statement stmt = connection.createStatement();
            String query = "CREATE TABLE test_zamowienia (id INT AUTO_INCREMENT, id_klienci INT, id_ksiazki INT, data VARCHAR(255), PRIMARY KEY (id))";
            stmt.execute(query);

            String query2 = "INSERT INTO test_zamowienia (id_klienci, id_ksiazki, data) VALUES(1 , 2 , '24072023')";
            stmt.executeUpdate(query2);

            String query3 = "SELECT * FROM test_zamowienia";

            ResultSet rs = stmt.executeQuery(query3);
            String output = "";
            while (rs.next()) {
                output = rs.getInt(1) + "  " + rs.getInt(2) + "  " + rs.getInt(3) + "  " + rs.getString(4);
            }

            String expectedOutput = "1  1  2  24072023";
            assertEquals(expectedOutput, output);

            String query4 = "DROP table test_zamowienia";
            stmt.execute(query4);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void deleteOrder() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Połączono z bazą danych");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {

            Statement stmt = connection.createStatement();
            String query = "CREATE TABLE test_zamowienia (id INT AUTO_INCREMENT, id_klienci INT, id_ksiazki INT, data VARCHAR(255), PRIMARY KEY (id))";
            stmt.execute(query);

            String query2 = "INSERT INTO test_zamowienia (id_klienci, id_ksiazki, data) VALUES(1 , 2 , '24072023'), (2 , 3 , '25072023')";
            stmt.execute(query2);

            String query3 = "DELETE FROM test_zamowienia WHERE id = 1";
            stmt.executeUpdate(query3);
            String query4 = "SELECT * FROM test_zamowienia WHERE id = 2";

            ResultSet rs = stmt.executeQuery(query4);
            String output = "";
            while (rs.next()) {
                output = rs.getInt(1) + "  " + rs.getInt(2) + "  " + rs.getInt(3) + "  " + rs.getString(4);
            }

            String expectedOutput = "2  2  3  25072023";
            assertEquals(expectedOutput, output);

            String query5 = "DROP table test_zamowienia";
            stmt.execute(query5);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void updateOrder() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Połączono z bazą danych");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {

            Statement stmt = connection.createStatement();
            String query = "CREATE TABLE test_zamowienia (id INT AUTO_INCREMENT, id_klienci INT, id_ksiazki INT, data VARCHAR(255), PRIMARY KEY (id))";
            stmt.execute(query);

            String query2 = "INSERT INTO test_zamowienia (id_klienci, id_ksiazki, data) VALUES(1 , 2 , '24072023')";
            stmt.execute(query2);

            String query3 = "UPDATE test_zamowienia SET id_klienci = 2, id_ksiazki = 3, data = '25072023' WHERE id = 1";
            stmt.executeUpdate(query3);
            String query4 = "SELECT * FROM test_zamowienia WHERE id = 1";

            ResultSet rs = stmt.executeQuery(query4);
            String output = "";
            while (rs.next()) {
                output = rs.getInt(1) + "  " + rs.getInt(2) + "  " + rs.getInt(3) + "  " + rs.getString(4);
            }

            String expectedOutput = "1  2  3  25072023";
            assertEquals(expectedOutput, output);

            String query5 = "DROP table test_zamowienia";
            stmt.execute(query5);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}