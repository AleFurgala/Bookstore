package com.example;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;

public class StatisticsTest {
    Connection connection;

    @Test
    void generateReportTest(){
        Statistics statistics = new Statistics(connection);
        String sciezkaDoPliku = "bookstoreTest.txt";
        try {
            FileWriter fileWriter = new FileWriter(sciezkaDoPliku);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String zapisDoPliku = "Przykładowy tekst zapisany w pliku";

                bufferedWriter.write(zapisDoPliku);

            bufferedWriter.close();
            System.out.println("Dane zostały zapisane do pliku " + sciezkaDoPliku);


            System.out.println("Plik został pomyślnie utworzony i zapisany.");
        } catch (IOException e) {
            System.err.println("Wystąpił błąd podczas tworzenia pliku: " + e.getMessage());
        }

    }

}


