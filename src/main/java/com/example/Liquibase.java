package com.example;

import liquibase.exception.LiquibaseException;

import java.sql.Connection;

public class Liquibase {
    private static DbConnection dbConnection;
    static Connection connection = dbConnection.getConnection();
    public static void createDb() throws LiquibaseException {



    }
}
