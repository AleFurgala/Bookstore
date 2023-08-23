package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Book {

    private Connection connection;

    public Book(Connection connection) {
        this.connection = connection;
    }

    private static int id;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Book.id = id;
    }


    public void showAllBooks() throws SQLException{
        String query = "select * from ksiazki";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("id  | Autor książki       | Tytuł książki      | cena  | ilość");
            System.out.println("---------------------------------------------------------------");
            System.out.println();
            while (rs.next()) {
                int column1 = rs.getInt(1);
                String column2 = rs.getString(2);
                String column3 = rs.getString(3);
                int column4 = rs.getInt(4);
                int column5 = rs.getInt(5);

                System.out.printf("%-5d %-20s %-20s %-5d %-5d%n", column1, column2, column3, column4, column5);
            }
            System.out.println("---------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void showAvailableBooks() throws SQLException {
        String query = "SELECT * FROM ksiazki WHERE ilosc > 0";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("id  | Autor książki       | Tytuł książki      | cena  | ilość");
            System.out.println("---------------------------------------------------------------");
            System.out.println();
            while (rs.next()) {
                int column1 = rs.getInt(1);
                String column2 = rs.getString(2);
                String column3 = rs.getString(3);
                int column4 = rs.getInt(4);
                int column5 = rs.getInt(5);

                System.out.printf("%-5d %-20s %-20s %-5d %-5d%n", column1, column2, column3, column4, column5);
            }
            System.out.println("---------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showBooksByTitleOrAuthor(String titleOrAuthor) throws SQLException {
        String query = "SELECT * FROM ksiazki WHERE tytul LIKE '%" + titleOrAuthor + "%' OR autor LIKE '%" + titleOrAuthor + "%' ";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("id  | Autor książki       | Tytuł książki      | cena  | ilość");
            System.out.println("---------------------------------------------------------------");
            System.out.println();
            while (rs.next()) {
                int column1 = rs.getInt(1);
                String column2 = rs.getString(2);
                String column3 = rs.getString(3);
                int column4 = rs.getInt(4);
                int column5 = rs.getInt(5);

                System.out.printf("%-5d %-20s %-20s %-5d %-5d%n", column1, column2, column3, column4, column5);
            }
            System.out.println("---------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addBook(String title, String author, int price, int amount) throws SQLException {

        try {
            Statement stmt = connection.createStatement();
            String query = "INSERT INTO ksiazki(tytul, autor, cena,ilosc) VALUES('" + title + "' , '" + author + "' , " + price + " , " + amount + ")";
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteBook(int bookToDelete) throws SQLException {
        try {
            Statement stmt = connection.createStatement();
            String query = "DELETE FROM ksiazki WHERE id = '" + bookToDelete + "'";
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateBook(int id, String title, String author, int price, int amount) throws SQLException {
        try {

            Statement stmt = connection.createStatement();

            String query = "UPDATE ksiazki SET tytul = '" + title + "', autor = '" + author + "', cena =  '" + price + "', ilosc =  '" + amount + "' WHERE id = '" + id + "'";
            stmt.executeUpdate(query);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteAmount(int number) throws SQLException {
        try {
            String query1 = "SELECT ilosc FROM ksiazki WHERE id = " + number + "";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query1);

            int amountOfBooks = 0;
            while (rs.next()) {
                amountOfBooks = rs.getInt(1);
                if (amountOfBooks <= 0) {
                    System.out.println("Brak książek w magazynie");
                } else {
                    amountOfBooks = amountOfBooks - 1;
                }
            }
            String query = "UPDATE ksiazki SET ilosc = '" + amountOfBooks + "' WHERE id = '" + number + "'";
            stmt.executeUpdate(query);

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
