import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Book {
    private static String titleOrAuthor;

    public static String getTitleOrAuthor() {
        return titleOrAuthor;
    }

    public static void setTitleOrAuthor(String titleOrAuthor) {
        Book.titleOrAuthor = titleOrAuthor;
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

            // System.out.println("Wprowadź numer: ");
            //int dodajNumer = scanner.nextInt();

            Scanner scanner1 = new Scanner(System.in);

            System.out.println("Wprowadź tytuł: ");

            String dodajTytul = scanner1.nextLine();
            System.out.println("Wprowadź autora: ");
            String dodajAutora = scanner1.nextLine();
            System.out.println("Wprowadź cenę: ");
            int dodajCena = scanner.nextInt();


            String query = "INSERT INTO ksiazki(tytul, autor, cena) VALUES('" + dodajTytul + "' , '" + dodajAutora + "' , " + dodajCena + ")";

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
            int idKsiazki = scanner.nextInt();


            String query = "DELETE FROM ksiazki WHERE id = '" + idKsiazki + "'";

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
