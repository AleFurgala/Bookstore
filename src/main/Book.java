package main;

import java.sql.*;
import java.util.Scanner;

public class Book{

    private Connection connection;

    public Book(Connection connection) {
        this.connection = connection;
    }

    private static int id;
    private static String title;
    private static String author;
    private static int price;

    private static String titleOrAuthor;
    private static int amount;

    public static int getAmount() {
        return amount;
    }

    public static void setAmount(int amount) {
        Book.amount = amount;
    }

    public static String getTitle() {
        return title;
    }

    public static void setTitle(String title) {
        Book.title = title;
    }

    public static String getAuthor() {
        return author;
    }

    public static void setAuthor(String author) {
        Book.author = author;
    }

    public static int getPrice() {
        return price;
    }

    public static void setPrice(int price) {
        Book.price = price;
    }


    public static String getTitleOrAuthor() {
        return titleOrAuthor;
    }

    public static void setTitleOrAuthor(String titleOrAuthor) {
        Book.titleOrAuthor = titleOrAuthor;
    }

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

    public void showBooksByTitleOrAuthor() throws SQLException{
        String query = "SELECT * FROM ksiazki WHERE tytul LIKE '%" + getTitleOrAuthor() + "%' OR autor LIKE '%" + getTitleOrAuthor() + "%' ";
        try {

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Podaj tytuł albo autora: ");
            Scanner scanner = new Scanner(System.in);
            setTitleOrAuthor(scanner.nextLine());

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

    public void addBook() throws SQLException{

        try {

            Statement stmt = connection.createStatement();

            Scanner scanner = new Scanner(System.in);

            Scanner scanner1 = new Scanner(System.in);

            System.out.println("Wprowadź tytuł: ");
            setTitle(scanner1.nextLine());
            System.out.println("Wprowadź autora: ");
            setAuthor(scanner1.nextLine());
            System.out.println("Wprowadź cenę: ");
            setPrice(scanner.nextInt());
            System.out.println("Wprowadź ilosc: ");
            setAmount(scanner.nextInt());
            String query = "INSERT INTO ksiazki(tytul, autor, cena,ilosc) VALUES('" + getTitle() + "' , '" + getAuthor() + "' , " + getPrice() + " , " + getAmount() + ")";
            stmt.executeUpdate(query);

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public  void deleteBook()throws SQLException {
        try {

            Statement stmt = connection.createStatement();
            System.out.println("Wprowadź numer id książki którą chcesz usunąć: ");
            Scanner scanner = new Scanner(System.in);
            setId(scanner.nextInt());

            String query = "DELETE FROM ksiazki WHERE id = '" + getId() + "'";
            stmt.executeUpdate(query);

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateBook() throws SQLException{
        try {

            Statement stmt = connection.createStatement();
            Scanner scanner = new Scanner(System.in);
            Scanner scanner1 = new Scanner(System.in);

            System.out.println("Wprowadź id książki, którą chcesz edytować");
            setId(scanner.nextInt());

            System.out.println("Wprowadź tytuł: ");
            setTitle(scanner1.nextLine());
            System.out.println("Wprowadź autora: ");
            setAuthor(scanner1.nextLine());
            System.out.println("Wprowadź cenę: ");
            setPrice(scanner.nextInt());
            System.out.println("Wprowadź ilosc: ");
            setAmount(scanner.nextInt());


            String query = "UPDATE ksiazki SET tytul = '" + getTitle() + "', autor = '" + getAuthor() + "', cena =  '" + getPrice() + "', ilosc =  '" + getAmount() + "' WHERE id = '" + getId() + "'";
            stmt.executeUpdate(query);

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteAmount(int number) throws SQLException{

        try {
           String query1 ="SELECT ilosc FROM ksiazki WHERE id = " + number + "";
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
