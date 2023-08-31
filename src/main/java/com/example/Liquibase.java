package com.example;

import liquibase.exception.LiquibaseException;

import java.sql.Connection;

public class Liquibase {
    private static JdbConnection jdbConnection;
    static Connection connection = jdbConnection.getConnection();
    public static void createDb() throws LiquibaseException {



    }
}
