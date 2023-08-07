package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class Order {
    public Order(Connection connection) {
        this.connection = connection;
    }

    private Connection connection;

    private static int id;
    private static int idKsiazki;

    private static int idKlienci;

    public Order() {

    }


    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Order.id = id;
    }

    public static int getIdKsiazki() {
        return idKsiazki;
    }

    public static void setIdKsiazki(int idKsiazki) {
        Order.idKsiazki = idKsiazki;
    }

    public static int getIdKlienci() {
        return idKlienci;
    }

    public static void setIdKlienci(int idKlienci) {
        Order.idKlienci = idKlienci;
    }


    public void showAllOrder() throws SQLException {
        String query = "SELECT zamowienia.id,zamowienia.data, klienci.imie, klienci.nazwisko, ksiazki.tytul FROM zamowienia INNER JOIN ksiazki ON ksiazki.id = zamowienia.id_ksiazki INNER JOIN klienci ON klienci.id = zamowienia.id_klienci";
        try {

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("id | klient | książka | data ");
            System.out.println();
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) +"  " + rs.getString(3) + "  " + rs.getString(4) + "  " + rs.getString(5));

            System.out.println("********************************************************");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showOrderById(int id) throws SQLException {

        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT zamowienia.id, zamowienia.data, klienci.imie, klienci.nazwisko, ksiazki.tytul, ksiazki.autor FROM klienci, ksiazki, zamowienia WHERE zamowienia.id = '" + id + "' AND zamowienia.id_klienci = klienci.id AND zamowienia.id_ksiazki = ksiazki.id";
            ResultSet rs = stmt.executeQuery(query);
            System.out.println(" id zamówienia | data zamowienia | imie klienta | nazwisko klient | tytuł książki | autor ksiazki ");
            System.out.println();
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  "+ rs.getString(3) + "  " + rs.getString(4) + "  " + rs.getString(5) + "  " + rs.getString(6));

            System.out.println("********************************************************");

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public int addOrder(int idClients, int idBooks){
        try {
            Statement stmt = connection.createStatement();

            String query = "INSERT INTO zamowienia(id_klienci, id_ksiazki, data) VALUES(" + idClients + " , " + idBooks + " , " + getDate() + ")";

            stmt.executeUpdate(query);

        } catch (Exception e) {
            System.out.println(e);
        }
        return getIdKsiazki();
    }

    public void deleteOrder(int id) throws SQLException {
        try {
            Statement stmt = connection.createStatement();

            String query = "DELETE FROM zamowienia WHERE id = '" + id + "'";
            stmt.executeUpdate(query);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateOrder(int id, int idClients, int idBooks) throws SQLException {
        try {
            Statement stmt = connection.createStatement();

            String query = "UPDATE zamowienia SET id_klienci = '" + idClients + "', id_ksiazki = '" + idBooks + "' WHERE id = '" + id + "'";
            stmt.executeUpdate(query);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        return dayOfMonth + "" + month + "" +year;
    }

}
