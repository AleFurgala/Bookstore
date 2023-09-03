package com.example;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;

public class LiquibaseConfiguration {

    public LiquibaseConfiguration(Connection connection) {
        this.connection = connection;
    }

    private Liquibase liquibase;

    private Connection connection;

    public void addLiquibase() throws LiquibaseException {
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
        liquibase = new Liquibase("liquibase/changelog.xml", new ClassLoaderResourceAccessor(), database);
        liquibase.update("");

    }

    public void updateLiquibase() throws LiquibaseException {
        liquibase.update("");
    }

}
