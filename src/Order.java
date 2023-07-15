import java.sql.*;
import java.util.Scanner;

public class Order {
    public Order(Connection connection) {
        this.connection = connection;
    }

    private Connection connection;

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


    public void showAllOrder() throws SQLException {
        String query = "SELECT zamowienia.id, klienci.imie, klienci.nazwisko, ksiazki.tytul FROM zamowienia INNER JOIN ksiazki ON ksiazki.id = zamowienia.id_ksiazki INNER JOIN klienci ON klienci.id = zamowienia.id_klienci";
        try {

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("id | klient | książka  ");
            System.out.println();
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4));

            System.out.println("********************************************************");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showOrderById() throws SQLException {

        try {
            Statement stmt = connection.createStatement();
            System.out.println("Podaj id zamowienia: ");
            Scanner scanner = new Scanner(System.in);
            setId(scanner.nextInt());
            String query = "SELECT zamowienia.id, klienci.imie, klienci.nazwisko, ksiazki.tytul, ksiazki.autor FROM klienci, ksiazki, zamowienia WHERE zamowienia.id = '" + getId() + "' AND zamowienia.id_klienci = klienci.id AND zamowienia.id_ksiazki = ksiazki.id";
            ResultSet rs = stmt.executeQuery(query);
            System.out.println(" id zamówienia | imie klienta | nazwisko klient | tytuł książki | autor ksiazki ");
            System.out.println();
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4) + "  " + rs.getString(5));

            System.out.println("********************************************************");

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public int addOrder() throws SQLException {
        try {

            Statement stmt = connection.createStatement();

            Scanner scanner = new Scanner(System.in);
            Scanner scanner1 = new Scanner(System.in);

            System.out.println("Wprowadź id  klienta: ");
            setIdKlienci(scanner1.nextInt());
            System.out.println("Wprowadź id książki: ");
            setIdKsiazki(scanner.nextInt());

            String query = "INSERT INTO zamowienia(id_klienci, id_ksiazki) VALUES(" + getIdKlienci() + " , " + getIdKsiazki() + ")";

            stmt.executeUpdate(query);


        } catch (Exception e) {
            System.out.println(e);
        }
        return getIdKsiazki();
    }

    public void deleteOrder() throws SQLException {
        try {

            Statement stmt = connection.createStatement();
            System.out.println("Wprowadź numer id zamowienia które chcesz usunąć: ");
            Scanner scanner = new Scanner(System.in);
            setId(scanner.nextInt());

            String query = "DELETE FROM zamowienia WHERE id = '" + getId() + "'";
            stmt.executeUpdate(query);

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateOrder() throws SQLException {
        try {

            Statement stmt = connection.createStatement();

            Scanner scanner = new Scanner(System.in);


            System.out.println("Wprowadź id zamówienia, które chcesz edytować:");
            setId(scanner.nextInt());

            System.out.println("Wprowadź ID Kliena: ");
            setIdKlienci(scanner.nextInt());

            System.out.println("Wprowadź ID Książki: ");
            setIdKsiazki(scanner.nextInt());


            String query = "UPDATE zamowienia SET id_klienci = '" + getIdKlienci() + "', id_ksiazki = '" + getIdKsiazki() + "' WHERE id = '" + getId() + "'";
            stmt.executeUpdate(query);


        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
