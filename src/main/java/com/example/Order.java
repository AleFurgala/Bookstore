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

    private static Long id;
    private static Long idKsiazki;

    private static Long idKlienci;

    public Order() {

    }


    public static Long getId() {
        return id;
    }

    public static void setId(Long id) {
        Order.id = id;
    }

    public static Long getIdKsiazki() {
        return idKsiazki;
    }

    public static void setIdKsiazki(Long idKsiazki) {
        Order.idKsiazki = idKsiazki;
    }

    public static Long getIdKlienci() {
        return idKlienci;
    }

    public static void setIdKlienci(Long idKlienci) {
        Order.idKlienci = idKlienci;
    }


    public void showAllOrder() throws SQLException {
        String query = "SELECT zamowienia.id,zamowienia.data, klienci.imie, klienci.nazwisko, ksiazki.tytul FROM zamowienia INNER JOIN ksiazki ON ksiazki.id = zamowienia.id_ksiazki INNER JOIN klienci ON klienci.id = zamowienia.id_klienci";
        try {

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("id   | Data                    | Klient                        | Książka ");
            System.out.println("------------------------------------------------------------------------------------------------------");
            System.out.println();

            while (rs.next()) {
                Long column1 = rs.getLong(1);
                String column2 = rs.getString(2);
                String column3 = rs.getString(3);
                String column4 = rs.getString(4);
                String column5 = rs.getString(5);

                System.out.printf("%-5d %-25s %-15s %-15s %-15s%n", column1, column2, column3, column4, column5);

            }
            System.out.println("------------------------------------------------------------------------------------------------------");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showOrderById(Long id) throws SQLException {

        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT zamowienia.id, zamowienia.data, klienci.imie, klienci.nazwisko, ksiazki.tytul, ksiazki.autor FROM klienci, ksiazki, zamowienia WHERE zamowienia.id = '" + id + "' AND zamowienia.id_klienci = klienci.id AND zamowienia.id_ksiazki = ksiazki.id";
            ResultSet rs = stmt.executeQuery(query);


            System.out.println("id   | Data                    | Klient                        | Książka ");
            System.out.println("------------------------------------------------------------------------------------------------------");
            System.out.println();

            while (rs.next()) {
                Long column1 = rs.getLong(1);
                String column2 = rs.getString(2);
                String column3 = rs.getString(3);
                String column4 = rs.getString(4);
                String column5 = rs.getString(5);
                String column6 = rs.getString(6);

                System.out.printf("%-5d %-25s %-15s %-15s %-15s %-15s%n", column1, column2, column3, column4, column5, column6);

            }
            System.out.println("------------------------------------------------------------------------------------------------------");

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void addOrder(Long idClients, Long idBooks) {
        try {
            Statement stmt = connection.createStatement();

            String query = "INSERT INTO zamowienia(id_klienci, id_ksiazki, data) VALUES(" + idClients + " , " + idBooks + " , " + getDate() + ")";

            stmt.executeUpdate(query);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteOrder(Long id) throws SQLException {
        try {
            Statement stmt = connection.createStatement();

            String query = "DELETE FROM zamowienia WHERE id = '" + id + "'";
            stmt.executeUpdate(query);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateOrder(Long id, Long idClients, Long idBooks) throws SQLException {
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
