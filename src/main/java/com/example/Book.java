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


    public void showAllBooks() throws SQLException {
        String query = "select * from ksiazki";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("id  | Autor ksiązki | Tytuł książki | cena | ilosc");
            System.out.println();
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) +
                        "  " + rs.getString(4) + "  " + rs.getInt(5));

            System.out.println("********************************************************");

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void showBooksByTitleOrAuthor(String titleOrAuthor) throws SQLException {
        String query = "SELECT * FROM ksiazki WHERE tytul LIKE '%" + titleOrAuthor + "%' OR autor LIKE '%" + titleOrAuthor + "%' ";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("id  | Autor ksiązki | Tytuł książki | cena | ilosc");
            System.out.println();
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) +
                        "  " + rs.getString(4) + "  " + rs.getInt(5));
            System.out.println("********************************************************");

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
            System.out.println("id | Autor ksiązki | Tytuł książki | cena | ilosc");
            System.out.println();

            int AmountOfBooks = 0;
            while (rs.next()) {
                AmountOfBooks = rs.getInt(1);
                AmountOfBooks = AmountOfBooks - 1;
            }

            System.out.println("********************************************************");

            String query = "UPDATE ksiazki SET ilosc = '" + AmountOfBooks + "' WHERE id = '" + number + "'";
            stmt.executeUpdate(query);

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
