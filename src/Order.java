import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Order {

    private static int id;
    private static int idKsiazki;

    private static int idKlienci;

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



    public static void showAllOrder() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksiegarnia", "root", "");
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT zamowienia.id, klienci.imie, klienci.nazwisko, ksiazki.tytul FROM zamowienia,ksiazki,klienci WHERE ksiazki.id = zamowienia.id_ksiazki AND klienci.id = zamowienia.id_klienci");
            System.out.println("id  | klient | książka  ");
            System.out.println();
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4));

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

            System.out.println("Wprowadź id  klienta: ");

            setIdKlienci(scanner1.nextInt());
            System.out.println("Wprowadź id książki: ");

            setIdKsiazki(scanner.nextInt());


            String query = "INSERT INTO zamowienia(id_klienci, id_ksiazki) VALUES(" + getIdKlienci() + " , " + getIdKsiazki() + ")";

            stmt.executeUpdate(query);

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
