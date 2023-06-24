import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Book{

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
    public static void showAllBooksByTitle() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksiegarnia", "root", "");
            Statement stmt = con.createStatement();

            System.out.println("Wprowadź tytuł książki: ");
            Scanner scanner = new Scanner(System.in);
            String tytulKsiazki = scanner.nextLine();

            ResultSet rs = stmt.executeQuery("SELECT * FROM `ksiazki` WHERE `tytul` ='" + tytulKsiazki + "'");
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


            String query= "DELETE FROM ksiazki WHERE id = '" + idKsiazki +"'";

            stmt.executeUpdate(query);


            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
