import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminAccounts {

    private Connection connection;

    public AdminAccounts(Connection connection) {
        this.connection = connection;
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
}

