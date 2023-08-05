package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Client {
    public Client(Connection connection) {
        this.connection = connection;
    }

    private Connection connection;

    public void showAllClients() throws SQLException {
        String query = "select * from klienci";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("id  | Imie klienta | Nazwisko Klienta | adres ");
            System.out.println();
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) +
                        "  " + rs.getString(4));

            System.out.println("********************************************************");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showClientByNameOrSurname(String nameOrSurname) throws SQLException {
        String query = "SELECT * FROM klienci WHERE imie LIKE '%" + nameOrSurname + "%' OR nazwisko LIKE '%" + nameOrSurname + "%' ";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);


            System.out.println("id  | Imię Kliena | Nazwisko Klienta | adres ");
            System.out.println();
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) +
                        "  " + rs.getString(4));

            System.out.println("********************************************************");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addClient(String name, String surname, String address) throws SQLException {
        try {
            Statement stmt = connection.createStatement();

            String query = "INSERT INTO klienci(imie, nazwisko, adres) VALUES('" + name + "' , '" + surname + "' , '" + address + "')";

            stmt.executeUpdate(query);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteClient(int clientToDelete) throws SQLException {
        try {
            Statement stmt = connection.createStatement();

            String query = "DELETE FROM klienci WHERE id = '" + clientToDelete + "'";

            stmt.executeUpdate(query);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateClient(int id, String name, String surname, String address) throws SQLException {
        try {
            Statement stmt = connection.createStatement();

            String query = "UPDATE klienci SET imie = '" + name + "', nazwisko = '" + surname + "', adres =  '" + address + "' WHERE id = '" + id + "'";

            stmt.executeUpdate(query);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
