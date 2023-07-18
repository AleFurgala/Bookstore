import javax.crypto.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AdminAccounts {

    private Connection connection;


    public AdminAccounts(Connection connection) {
        this.connection = connection;
    }

    private String password;
    private String login;

    private String username;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPasswordBasedLogin(String login) throws SQLException {
        String query = "SELECT haslo FROM konta_administratorow WHERE login =  '" + login + "'";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println();
            while (rs.next())
               return  rs.getString(1);

        } catch (Exception e) {
            System.out.println(e);
        }
        return "0";
    }
    public String getNameBasedLogin(String login) throws SQLException {
        String query = "SELECT nazwa_uzytkownika FROM konta_administratorow WHERE login =  '" + login + "'";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println();
            while (rs.next())
                return  rs.getString(1);

        } catch (Exception e) {
            System.out.println(e);
        }
        return "0";
    }

    public void addAdminAccount() throws SQLException{

        try {

            Statement stmt = connection.createStatement();

            Scanner scanner = new Scanner(System.in);

            System.out.println("Wprowadź login: ");
            setLogin(scanner.nextLine());
            System.out.println("Wprowadź haslo: ");
            setPassword(scanner.nextLine());
            System.out.println("Wprowadź nazwę użytkownika: ");
            setUsername(scanner.nextLine());

            String query = "INSERT INTO konta_administratorow(login, haslo, nazwa_uzytkownika) VALUES('" + getLogin() + "' , '" + dataEncryption(getPassword()) + "' , '" + getUsername() + "')";
            stmt.executeUpdate(query);

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public byte[] dataEncryption(String pw) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        byte[] encryptedData = cipher.doFinal(pw.getBytes());

//        cipher.init(Cipher.DECRYPT_MODE,secretKey);
//        byte[] decryptedData = cipher.doFinal(encryptedData);
//
//        System.out.println(new String(decryptedData));

        return encryptedData;
    }

}

