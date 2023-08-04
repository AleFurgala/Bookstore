package com.example;

import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientTest {

    private static final String url = "jdbc:mysql://localhost:3306/ksiegarnia";
    private static final String user = "root";
    private static final String password = "";

    private Connection connection;
    @Test
    void showAllClients()throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Połączono z bazą danych");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {

            Statement stmt = connection.createStatement();
            String query = "CREATE TABLE test_klienci (id INT AUTO_INCREMENT, imie VARCHAR(255), nazwisko VARCHAR(255), adres VARCHAR(255), PRIMARY KEY (id))";
            stmt.execute(query);

            String query2 = "INSERT INTO test_klienci (imie, nazwisko, adres) VALUES('Jan' ,'Nowak', 'Rzeszow'), ('Krystyna' , 'Kowalska' , 'Warszawa')";
            stmt.execute(query2);

            String query3 = "SELECT * FROM test_klienci";

            ResultSet rs = stmt.executeQuery(query3);
            String output = "";
            while (rs.next()) {
                output = output + rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4);
            }

            String expectedOutput = "1  Jan  Nowak  Rzeszow" +
                    "2  Krystyna  Kowalska  Warszawa";
            assertEquals(expectedOutput, output);

            String query4 = "DROP table test_klienci";
            stmt.execute(query4);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void showClientByNameOrSurname() throws SQLException {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Połączono z bazą danych");
            } catch (Exception e) {
                System.out.println(e);
            }
            try {

                Statement stmt = connection.createStatement();
                String query = "CREATE TABLE test_klienci (id INT AUTO_INCREMENT, imie VARCHAR(255), nazwisko VARCHAR(255), adres VARCHAR(255), PRIMARY KEY (id))";
                stmt.execute(query);

                String query2 = "INSERT INTO test_klienci (imie, nazwisko, adres) VALUES('Jan' ,'Nowak', 'Rzeszow'), ('Krystyna' , 'Kowalska' , 'Warszawa')";
                stmt.execute(query2);

                String query3 = "SELECT * FROM test_klienci WHERE imie LIKE '%Jan%' OR '%Nowak%' OR nazwisko LIKE '%Jan%' OR '%Nowak%'";
                ResultSet rs = stmt.executeQuery(query3);
                String output = "";
                while (rs.next()) {
                    output = rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4);
                }

                String expectedOutput = "1  Jan  Nowak  Rzeszow";
                assertEquals(expectedOutput, output);

                String query4 = "DROP table test_klienci";
                stmt.execute(query4);
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }

    }

    @Test
    void addClient() throws SQLException {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Połączono z bazą danych");
            } catch (Exception e) {
                System.out.println(e);
            }
            try {

                Statement stmt = connection.createStatement();
                String query = "CREATE TABLE test_klienci (id INT AUTO_INCREMENT, imie VARCHAR(255), nazwisko VARCHAR(255), adres VARCHAR(255), PRIMARY KEY (id))";
                stmt.execute(query);

                String query2 = "INSERT INTO test_klienci (imie, nazwisko, adres) VALUES('Jan' ,'Nowak', 'Rzeszow')";
                stmt.execute(query2);

                String query3 = "SELECT * FROM test_klienci";

                ResultSet rs = stmt.executeQuery(query3);
                String output = "";
                while (rs.next()) {
                    output = rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4);
                }

                String expectedOutput = "1  Jan  Nowak  Rzeszow";
                assertEquals(expectedOutput, output);

                String query4 = "DROP table test_klienci";
                stmt.execute(query4);
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
    }

    @Test
    void deleteClient() throws SQLException {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Połączono z bazą danych");
            } catch (Exception e) {
                System.out.println(e);
            }
            try {

                Statement stmt = connection.createStatement();
                String query = "CREATE TABLE test_klienci (id INT AUTO_INCREMENT, imie VARCHAR(255), nazwisko VARCHAR(255), adres VARCHAR(255), PRIMARY KEY (id))";
                stmt.execute(query);

                String query2 = "INSERT INTO test_klienci (imie, nazwisko, adres) VALUES('Jan' ,'Nowak', 'Rzeszow'), ('Krystyna' , 'Kowalska' , 'Warszawa')";
                stmt.execute(query2);

                String query3 = "DELETE FROM test_klienci WHERE id = 1";
                stmt.executeUpdate(query3);

                String query4 = "SELECT * FROM test_klienci";
                ResultSet rs = stmt.executeQuery(query4);
                String output = "";
                while (rs.next()) {
                    output = rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4);
                }

                String expectedOutput = "2  Krystyna  Kowalska  Warszawa";
                assertEquals(expectedOutput, output);

                String query5 = "DROP table test_klienci";
                stmt.execute(query5);
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
    }

    @Test
    void updateClient() throws SQLException {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Połączono z bazą danych");
            } catch (Exception e) {
                System.out.println(e);
            }
            try {

                Statement stmt = connection.createStatement();
                String query = "CREATE TABLE test_klienci (id INT AUTO_INCREMENT, imie VARCHAR(255), nazwisko VARCHAR(255), adres VARCHAR(255), PRIMARY KEY (id))";
                stmt.execute(query);

                String query2 = "INSERT INTO test_klienci (imie, nazwisko, adres) VALUES('Jan' ,'Nowak', 'Rzeszow')";
                stmt.execute(query2);

                String query3 = "UPDATE test_klienci SET imie = 'Krystyna', nazwisko = 'Kowalska', adres = 'Warszawa' WHERE id = 1";
                stmt.executeUpdate(query3);
                String query4 = "SELECT * FROM test_klienci WHERE id = 1";

                ResultSet rs = stmt.executeQuery(query4);
                String output = "";
                while (rs.next()) {
                    output = rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4);
                }

                String expectedOutput = "1  Krystyna  Kowalska  Warszawa";
                assertEquals(expectedOutput, output);

                String query5 = "DROP table test_klienci";
                stmt.execute(query5);
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
    }
}

