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


}
