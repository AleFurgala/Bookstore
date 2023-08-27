package com.example;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class AdminAccountsTest {
    Connection connection;

    @Test
    void dataEncryption() throws Exception {
        AdminAccounts adminAccounts = new AdminAccounts(connection);
        assertEquals("zJc4Y8ajxQ5hpxa4RtRi6g==", adminAccounts.dataEncryption("a"));
    }

    @Test
    void dataDecryption() throws Exception {
        AdminAccounts adminAccounts = new AdminAccounts(connection);
        assertEquals("a",adminAccounts.dataDecryption("zJc4Y8ajxQ5hpxa4RtRi6g=="));
    }
}
