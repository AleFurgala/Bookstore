package test;

import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {
    private static final String url = "jdbc:mysql://localhost:3306/ksiegarnia";
    private static final String user = "root";
    private static final String password = "";

    private Connection connection;

    @Test
    void showAllBooks() throws SQLException {

            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Połączono z bazą danych");
            } catch (Exception e) {
                System.out.println(e);
            }

            try {

                Statement stmt = connection.createStatement();
                String query = "CREATE TABLE test_ksiazki (id INT AUTO_INCREMENT, tytul VARCHAR(255), autor VARCHAR(255), cena INT, ilosc INT, PRIMARY KEY (id))";
                stmt.execute(query);

                String query2 = "INSERT INTO test_ksiazki (tytul, autor, cena, ilosc) VALUES('Water' , 'Paula Hawkins' , 23 , 2), ('xyz' , 'abc' , 30 , 3)";
                stmt.execute(query2);

                String query3 = "SELECT * FROM test_ksiazki";

                ResultSet rs = stmt.executeQuery(query3);
                String output = "";
                while (rs.next()) {
                    output = output + rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getInt(4)+ "  " + rs.getInt(5);
                }

                String expectedOutput = "1  Water  Paula Hawkins  23  2" +
                        "2  xyz  abc  30  3";
                assertEquals(expectedOutput, output);

                String query4 = "DROP table test_ksiazki";
                stmt.execute(query4);
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }

        }

    @Test
    void showBooksByTitleOrAuthor()throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Połączono z bazą danych");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {

            Statement stmt = connection.createStatement();
            String query = "CREATE TABLE test_ksiazki (id INT AUTO_INCREMENT, tytul VARCHAR(255), autor VARCHAR(255), cena INT, ilosc INT, PRIMARY KEY (id))";
            stmt.execute(query);

            String query2 = "INSERT INTO test_ksiazki (tytul, autor, cena, ilosc) VALUES('Water' , 'Paula Hawkins' , 23 , 2), ('xyz' , 'abc' , 30 , 3)";
            stmt.execute(query2);

            String query3 = "SELECT * FROM test_ksiazki WHERE tytul LIKE '%Water%' OR autor LIKE '%Paula Hawkins%'";
            ResultSet rs = stmt.executeQuery(query3);
            String output = "";
            while (rs.next()) {
                output = rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getInt(4)+ "  " + rs.getInt(5);
            }

            String expectedOutput = "1  Water  Paula Hawkins  23  2";
            assertEquals(expectedOutput, output);

            String query4 = "DROP table test_ksiazki";
            stmt.execute(query4);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void addBook() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Połączono z bazą danych");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {

            Statement stmt = connection.createStatement();
            String query = "CREATE TABLE test_ksiazki (id INT AUTO_INCREMENT, tytul VARCHAR(255), autor VARCHAR(255), cena INT, ilosc INT, PRIMARY KEY (id))";
            stmt.execute(query);

            String query2 = "INSERT INTO test_ksiazki (tytul, autor, cena, ilosc) VALUES('Water' , 'Paula Hawkins' , 23 , 2)";
            stmt.execute(query2);

            String query3 = "SELECT * FROM test_ksiazki";
            ResultSet rs = stmt.executeQuery(query3);

            String output = "";
            while (rs.next()) {
                output = rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getInt(4)+ "  " + rs.getInt(5);
            }

            String expectedOutput = "1  Water  Paula Hawkins  23  2";
            assertEquals(expectedOutput, output);

            String query4 = "DROP table test_ksiazki";
            stmt.execute(query4);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void deleteBook() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Połączono z bazą danych");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {

            Statement stmt = connection.createStatement();
            String query = "CREATE TABLE test_ksiazki (id INT AUTO_INCREMENT, tytul VARCHAR(255), autor VARCHAR(255), cena INT, ilosc INT, PRIMARY KEY (id))";
            stmt.execute(query);

            String query2 = "INSERT INTO test_ksiazki (tytul, autor, cena, ilosc) VALUES('Water' , 'Paula Hawkins' , 23 , 2), ('xyz' , 'abc' , 30 , 3)";
            stmt.execute(query2);
            String query3 = "DELETE FROM test_ksiazki WHERE id = 1";
            stmt.executeUpdate(query3);

            String query4 = "SELECT * FROM test_ksiazki";
            ResultSet rs = stmt.executeQuery(query4);
            String output = "";
            while (rs.next()) {
                output = rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getInt(4)+ "  " + rs.getInt(5);
            }

            String expectedOutput = "2  xyz  abc  30  3";
            assertEquals(expectedOutput, output);

            String query5 = "DROP table test_ksiazki";
            stmt.execute(query5);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void updateBook() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Połączono z bazą danych");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {

            Statement stmt = connection.createStatement();
            String query = "CREATE TABLE test_ksiazki (id INT AUTO_INCREMENT, tytul VARCHAR(255), autor VARCHAR(255), cena INT, ilosc INT, PRIMARY KEY (id))";
            stmt.execute(query);

            String query2 = "INSERT INTO test_ksiazki (tytul, autor, cena, ilosc) VALUES('Water' , 'Paula Hawkins' , 23 , 2)";
            stmt.execute(query2);
            String query3 = "UPDATE test_ksiazki SET tytul = 'Wind', autor = 'Paul', cena = 23, ilosc = 4 WHERE id = 1";
            stmt.executeUpdate(query3);
            String query4 = "SELECT * FROM test_ksiazki WHERE id = 1";
            ResultSet rs = stmt.executeQuery(query4);

            String output = "";

            while (rs.next()) {
                output = rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getInt(4)+ "  " + rs.getInt(5);
            }

            String expectedOutput = "1  Wind  Paul  23  4";
            assertEquals(expectedOutput, output);

            String query5 = "DROP table test_ksiazki";
            stmt.execute(query5);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void deleteAmount() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Połączono z bazą danych");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {

            Statement stmt = connection.createStatement();
            String query = "CREATE TABLE test_ksiazki (id INT AUTO_INCREMENT, tytul VARCHAR(255), autor VARCHAR(255), cena INT, ilosc INT, PRIMARY KEY (id))";
            stmt.execute(query);

            String query2 = "INSERT INTO test_ksiazki (tytul, autor, cena, ilosc) VALUES('Water' , 'Paula Hawkins' , 23 , 2)";
            stmt.execute(query2);
            String query3 = "SELECT ilosc FROM ksiazki WHERE id = 1";
            ResultSet rs = stmt.executeQuery(query3);
            int AmountOfBooks = 0;
            while (rs.next()) {
                AmountOfBooks = rs.getInt(1);
                AmountOfBooks = AmountOfBooks - 1;
            }
            String query4 = "UPDATE ksiazki SET ilosc = '" + AmountOfBooks + "' WHERE id = 1";
            stmt.executeUpdate(query4);

            String query5 = "SELECT * FROM test_ksiazki WHERE id = 1";
            ResultSet rs2 = stmt.executeQuery(query5);

            String output = "";

            while (rs2.next()) {
                output = rs2.getInt(1) + "  " + rs2.getString(2) + "  " + rs2.getString(3) + "  " + rs2.getInt(4)+ "  " + rs2.getInt(5);
            }

            String expectedOutput = "1  Water  Paula Hawkins  23  2";
            assertEquals(expectedOutput, output);

            String query6 = "DROP table test_ksiazki";
            stmt.execute(query6);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        }
}
