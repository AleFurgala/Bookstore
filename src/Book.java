import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Book {

    private static int id;
    private static String title;
    private static String author;
    private static int price;

    private static String titleOrAuthor;

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


    public static void showAllBooks() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksiegarnia", "root", "");
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from ksiazki");
            System.out.println("id  | Autor ksiązki | Tytuł książki | cena ");
            System.out.println();
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) +
                        "  " + rs.getString(4));

            System.out.println("********************************************************");

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void showBooksByTitleOrAuthor() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksiegarnia", "root", "");
            Statement stmt = con.createStatement();

            System.out.println("Podaj tytuł albo autora: ");
            Scanner scanner = new Scanner(System.in);
            setTitleOrAuthor(scanner.nextLine());

            ResultSet rs = stmt.executeQuery("SELECT * FROM ksiazki WHERE tytul LIKE '%" + getTitleOrAuthor() + "%' OR autor LIKE '%" + getTitleOrAuthor() + "%' ");
            System.out.println("id  | Autor ksiązki | Tytuł książki | cena ");
            System.out.println();
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) +
                        "  " + rs.getString(4));

            System.out.println("********************************************************");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void addBook() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksiegarnia", "root", "");
            Statement stmt = con.createStatement();

            Scanner scanner = new Scanner(System.in);


            Scanner scanner1 = new Scanner(System.in);

            System.out.println("Wprowadź tytuł: ");

            setTitle(scanner1.nextLine());
            System.out.println("Wprowadź autora: ");
            setAuthor(scanner1.nextLine());
            System.out.println("Wprowadź cenę: ");
            setPrice(scanner.nextInt());


            String query = "INSERT INTO ksiazki(tytul, autor, cena) VALUES('" + getTitle() + "' , '" + getAuthor() + "' , " + getPrice() + ")";

            stmt.executeUpdate(query);

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void deleteBook() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksiegarnia", "root", "");
            Statement stmt = con.createStatement();

            System.out.println("Wprowadź numer id książki którą chcesz usunąć: ");
            Scanner scanner = new Scanner(System.in);
            setId(scanner.nextInt());

            String query = "DELETE FROM ksiazki WHERE id = '" + getId() + "'";
            stmt.executeUpdate(query);

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateBook() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksiegarnia", "root", "");
            Statement stmt = con.createStatement();
            System.out.println("Wprowadź szukaną fraze: ");
            Scanner scanner = new Scanner(System.in);
            String fraza = scanner.nextLine();

            //ResultSet rs = stmt.executeQuery("SELECT * FROM `ksiazki` WHERE `tytul` LIKE `%Pan%`");
            ResultSet rs = stmt.executeQuery("SELECT * FROM ksiazki WHERE tytul LIKE '%" + fraza + "%' OR autor LIKE '%" + fraza + "%' ");
            System.out.println("id  | Autor ksiązki | Tytuł książki | cena ");
            System.out.println();
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) +
                        "  " + rs.getString(4));


            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
