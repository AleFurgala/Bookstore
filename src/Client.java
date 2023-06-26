import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Client {


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



    private  static String nameOrSurname;

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



    public static void showAllClients() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksiegarnia", "root", "");
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from klienci");
            System.out.println("id  | Imie klienta | Nazwisko Klienta | adres ");
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

    public static void showClientByNameOrSurname() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksiegarnia", "root", "");
            Statement stmt = con.createStatement();

            System.out.println("Podaj imię lub nazwisko: ");
            Scanner scanner = new Scanner(System.in);
            setNameOrSurname(scanner.nextLine());

            ResultSet rs = stmt.executeQuery("SELECT * FROM klienci WHERE imie LIKE '%" + getNameOrSurname() + "%' OR nazwisko LIKE '%" + getNameOrSurname() + "%' ");
            System.out.println("id  | Imię Kliena | Nazwisko Klienta | adres ");
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

    public static void addClient() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksiegarnia", "root", "");
            Statement stmt = con.createStatement();

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

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void deleteClient() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksiegarnia", "root", "");
            Statement stmt = con.createStatement();

            System.out.println("Wprowadź numer id klienta, którego chcesz usunąć: ");
            Scanner scanner = new Scanner(System.in);
            setId(scanner.nextInt());

            String query = "DELETE FROM klienci WHERE id = '" + getId() + "'";
            stmt.executeUpdate(query);

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateClient() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksiegarnia", "root", "");
            Statement stmt = con.createStatement();
            Scanner scanner = new Scanner(System.in);
            Scanner scanner1 = new Scanner(System.in);

            System.out.println("Wprowadź id klienta, którego chcesz edytować");
            setId(scanner.nextInt());

            System.out.println("Wprowadź imie: ");
            setName(scanner1.nextLine());
            System.out.println("Wprowadź nazwisko: ");
            setSurname(scanner1.nextLine());
            System.out.println("Wprowadź adres: ");
            setAdress(scanner.nextLine());

            String query = "UPDATE klienci SET name = '" + getName() + "', nazwisko = '" + getSurname() + "', adres =  '" + getAdress() + "' WHERE id = '" + getId() + "'";
            stmt.executeUpdate(query);

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }




}
