package com.example;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Statistics {
    public Statistics(Connection connection) {this.connection = connection;}
    private Connection connection;


    public void showOrderStatistic() throws SQLException {
        String query = "SELECT id_ksiazki, COUNT(*) AS liczba_zamowionych_ksiazek FROM zamowienia GROUP BY id_ksiazki";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println(" id książki       | liczba zamówionych książek ");
            System.out.println("---------------------------------------------------------------");
            System.out.println();
            while (rs.next()) {
                Long column1 = rs.getLong(1);
                Long column2 = rs.getLong(2);

                System.out.printf("%-20d %-20s%n", column1, column2);
            }
            System.out.println("---------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println(e);
        }

    }



}
