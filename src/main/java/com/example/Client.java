package com.example;

import java.sql.*;
import java.util.Scanner;

public class Client {
    public Client(Connection connection) {
        this.connection = connection;
    }

    private Connection connection;

    private static int id;
    private static String name;

    private static String surname;
    private static String adress;

    public static String getAdress() {
        return adress;
    }

    public static void setAdress(String adress) {
        Client.adress = adress;
    }


    private static String nameOrSurname;

    public static String getNameOrSurname() {
        return nameOrSurname;
    }

    public static void setNameOrSurname(String nameOrSurname) {
        Client.nameOrSurname = nameOrSurname;
    }


    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Client.id = id;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Client.name = name;
    }

    public static String getSurname() {
        return surname;
    }

    public static void setSurname(String surname) {
        Client.surname = surname;
    }


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

    public void showClientByNameOrSurname() throws SQLException {
        String query = "SELECT * FROM klienci WHERE imie LIKE '%" + getNameOrSurname() + "%' OR nazwisko LIKE '%" + getNameOrSurname() + "%' ";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Podaj imię lub nazwisko: ");
            Scanner scanner = new Scanner(System.in);
            setNameOrSurname(scanner.nextLine());

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

    public void addClient() throws SQLException {
        try {

            Statement stmt = connection.createStatement();

            Scanner scanner = new Scanner(System.in);
            Scanner scanner1 = new Scanner(System.in);

            System.out.println("Wprowadź imie: ");
            setName(scanner1.nextLine());
            System.out.println("Wprowadź nazwisko: ");
            setSurname(scanner1.nextLine());
            System.out.println("Wprowadź adres: ");
            setAdress(scanner.nextLine());

            String query = "INSERT INTO klienci(imie, nazwisko, adres) VALUES('" + getName() + "' , '" + getSurname() + "' , '" + getAdress() + "')";

            stmt.executeUpdate(query);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteClient() throws SQLException {
        try {

            Statement stmt = connection.createStatement();

            System.out.println("Wprowadź numer id klienta, którego chcesz usunąć: ");
            Scanner scanner = new Scanner(System.in);
            setId(scanner.nextInt());

            String query = "DELETE FROM klienci WHERE id = '" + getId() + "'";
            stmt.executeUpdate(query);


        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateClient() throws SQLException {
        try {

            Statement stmt = connection.createStatement();

            Scanner scanner = new Scanner(System.in);
            Scanner scanner1 = new Scanner(System.in);

            System.out.println("Wprowadź id klienta, którego chcesz edytować");
            setId(scanner.nextInt());

            System.out.println("Wprowadź imie: ");
            setName(scanner1.nextLine());

            System.out.println("Wprowadź nazwisko: ");
            setSurname(scanner1.nextLine());

            System.out.println("Wprowadź adres: ");
            setAdress(scanner1.nextLine());

            String query = "UPDATE klienci SET imie = '" + getName() + "', nazwisko = '" + getSurname() + "', adres =  '" + getAdress() + "' WHERE id = '" + getId() + "'";
            stmt.executeUpdate(query);


        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
