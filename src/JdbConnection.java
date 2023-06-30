import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbConnection {

    private static final String url = "jdbc:mysql://localhost:3306/ksiegarnia";
    private static final String user = "root";
    private static final String password = "";

    private Connection connection;
    public Connection getConnection() {
        return connection;
    }



    public JdbConnection() throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Połączono z bazą danych");
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public void closeConnection() throws SQLException {
        if (connection != null) {
           try {
               connection.close();
               System.out.println("Połączenie z bazą danych jest zakończone");
           }catch (Exception e) {
               System.out.println(e);
           }

        }
    }

}
