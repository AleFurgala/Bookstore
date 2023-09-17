package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Statistics {
    public Statistics(Connection connection) {
        this.connection = connection;
    }

    private Connection connection;


    public void showOrderStatistic() throws SQLException {
        String query = "SELECT id_ksiazki, ksiazki.tytul, COUNT(*) AS liczba_zamowionych_ksiazek FROM zamowienia INNER JOIN ksiazki ON ksiazki.id = zamowienia.id_ksiazki GROUP BY id_ksiazki ORDER BY liczba_zamowionych_ksiazek DESC";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println(" id książki       |Tytuł                            | liczba zamówionych książek ");
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println();
            while (rs.next()) {
                Long column1 = rs.getLong(1);
                String column2 = rs.getString(2);
                Long column3 = rs.getLong(3);

                System.out.printf("%-20d %-35s %-20d%n", column1, column2, column3);
            }
            System.out.println("--------------------------------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void showClientStatistic() throws SQLException {
        String query = "SELECT id_klienci, klienci.imie, klienci.nazwisko, COUNT(*) AS najbardziej_aktywny_klient  FROM zamowienia INNER JOIN klienci ON klienci.id = zamowienia.id_klienci GROUP BY id_klienci ORDER BY najbardziej_aktywny_klient DESC";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println(" id klienta       | Imie                 | Nazwisko               | Najbardziej aktywny klient ");
            System.out.println("------------------------------------------------------------------------------------------------------");
            System.out.println();
            while (rs.next()) {
                Long column1 = rs.getLong(1);
                String column2 = rs.getString(2);
                String column3 = rs.getString(3);
                Long column4 = rs.getLong(4);

                System.out.printf("%-20d %-20s %-20s %-20d%n", column1,column2, column3, column4);
            }
            System.out.println("------------------------------------------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
