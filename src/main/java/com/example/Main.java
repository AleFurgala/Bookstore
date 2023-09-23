package com.example;

import java.sql.Connection;
import java.util.Scanner;


public class Main {

    static String adminLogin;

    public static void main(String[] args) throws Exception {

        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
        Book book = new Book(connection);
        Client client = new Client(connection);
        Order order = new Order(connection);
        AdminAccounts adminAccounts = new AdminAccounts(connection);
        Statistics statistics = new Statistics(connection);
        LiquibaseConfiguration liquibaseConfiguration = new LiquibaseConfiguration(connection);

        liquibaseConfiguration.addLiquibase();

        Scanner scanner = new Scanner(System.in);

        adminLogin = readValue(scanner, "Podaj login");
        String passwordUser = readValue(scanner, "Podaj haslo");

        String passwordFromDB = adminAccounts.getPasswordBasedLogin(adminLogin);
        String decryptedPasswordFromDB = adminAccounts.dataDecryption(passwordFromDB);

        if (passwordUser.equals(decryptedPasswordFromDB)) {

            System.out.println("Witaj " + adminAccounts.getNameBasedLogin(adminLogin));

            System.out.println("Program księgarnia. Wybierz menu: ");
            int menu;
            do {
                System.out.println("1.Książki \n" +
                        "2.Klienci \n" +
                        "3.Zamówienia \n" +
                        "4.Panel administratora \n" +
                        "5.Statystyki\n" +
                        "0.Zamknij program ");
                menu = Integer.parseInt(readValue(scanner, ""));
                switch (menu) {
                    case 1:
                        System.out.println("1.Wyświetl wszystkie książki \n" +
                                "2.Wyświetl dostępne książki \n" +
                                "3.Wyszukaj ksiażkę \n" +
                                "4.Dodaj książkę \n" +
                                "5.Usuń książkę \n" +
                                "6.Edytuj książke \n" +
                                "7.Powrót do glównego menu \n" +
                                "********************************************************");
                        int menu2 = Integer.parseInt(readValue(scanner, ""));
                        switch (menu2) {
                            case 1:
                                book.showAllBooks();
                                break;
                            case 2:
                                book.showAvailableBooks();
                                break;
                            case 3:
                                String titleOrAuthor = readValue(scanner, "Podaj tytuł lub autora ksiązki, której szukasz");

                                book.showBooksByTitleOrAuthor(titleOrAuthor);
                                break;
                            case 4:
                                String title = readValue(scanner, "Wprowadź tytuł: ");
                                String author = readValue(scanner, "Wprowadź autora: ");
                                int price = Integer.parseInt(readValue(scanner, "Wprowadź cenę: "));
                                int amount = Integer.parseInt(readValue(scanner, "Wprowadź ilosc: "));

                                Long idAdmin = adminAccounts.getIdBasedLogin(adminLogin);

                                book.addBook(title, author, price, amount, idAdmin);
                                break;
                            case 5:
                                book.showAllBooks();

                                Long bookId = Long.valueOf((readValue(scanner, "Wprowadź numer id książki którą chcesz usunąć: ")));

                                book.deleteBook(bookId);
                                break;
                            case 6:
                                book.showAllBooks();

                                Long id = Long.valueOf((readValue(scanner, "Wprowadź id książki, którąchcesz edytować ")));
                                title = readValue(scanner, "Wprowadź tytuł: ");
                                author = readValue(scanner, "Wprowadź autora: ");
                                price = Integer.parseInt(readValue(scanner, "Wprowadź cenę: "));
                                amount = Integer.parseInt(readValue(scanner, "Wprowadź ilosc: "));

                                book.updateBook(id, title, author, price, amount);
                                break;
                            case 7:
                                System.out.println("6.Powrót do glównego menu");
                                break;
                            default:
                                System.out.println("Wprowadziłeś błędny numer ");
                        }
                        break;
                    case 2:
                        System.out.println("1.Wyświetl wszystkich klienótw \n" +
                                "2.Wyszukaj klienta \n" +
                                "3.Dodaj klienta \n" +
                                "4.Usuń klienta \n" +
                                "5.Edytuj klienta \n" +
                                "6.Powrót do glównego menun \n" +
                                "********************************************************");
                        int menu3 = Integer.parseInt(readValue(scanner, " "));
                        switch (menu3) {
                            case 1:
                                client.showAllClients();
                                break;
                            case 2:
                                String nameOrSurname = readValue(scanner, "Podaj imię lub nazwisko: ");

                                client.showClientByNameOrSurname(nameOrSurname);
                                break;
                            case 3:
                                String name = readValue(scanner, "Wprowadź imie: ");
                                String surname = readValue(scanner, "Wprowadź nazwisko: ");
                                String address = readValue(scanner, "Wprowadź adres: ");
                                Long idAdmin = adminAccounts.getIdBasedLogin(adminLogin);
                                client.addClient(name, surname, address, idAdmin);
                                break;
                            case 4:
                                client.showAllClients();

                                int clientToDelete = Integer.parseInt(readValue(scanner, "Wprowadź numer id klienta, którego chcesz usunąć: "));

                                client.deleteClient(clientToDelete);
                                break;
                            case 5:
                                Long id = Long.valueOf((readValue(scanner, "Wprowadź id klienta, którego chcesz edytować")));
                                name = readValue(scanner, "Wprowadź imie: ");
                                surname = readValue(scanner, "Wprowadź nazwisko: ");
                                address = readValue(scanner, "Wprowadź adres: ");

                                client.updateClient(id, name, surname, address);
                                break;
                            case 6:
                                System.out.println("6.Powrót do glównego menu");
                                break;
                            default:
                                System.out.println("Wprowadziłeś błędny numer ");
                        }
                        break;
                    case 3:
                        System.out.println("1.Wyświetl wszystkie zamówienia \n" +
                                "2.Wyszukaj zamówienie \n" +
                                "3.Dodaj zamówienie \n" +
                                "4.Usuń zamówienie \n" +
                                "5.Edytuj zamówienie \n" +
                                "6.Powrót do glównego menu \n" +
                                "********************************************************");
                        int menu4 = Integer.parseInt(readValue(scanner, " "));
                        switch (menu4) {
                            case 1:
                                order.showAllOrder();
                                break;
                            case 2:
                                Long id = Long.valueOf((readValue(scanner, "Podaj id zamowienia: ")));

                                order.showOrderById(id);
                                break;
                            case 3:
                                client.showAllClients();

                                Long idClients = Long.valueOf((readValue(scanner, "Wprowadź id  klienta: ")));

                                book.showAvailableBooks();

                                Long idBooks = Long.valueOf((readValue(scanner, "Wprowadź id książki: ")));
                                Long idAdmin = adminAccounts.getIdBasedLogin(adminLogin);
                                order.addOrder(idClients, idBooks, idAdmin);
                                book.deleteAmount(idBooks);
                                break;
                            case 4:
                                order.showAllOrder();

                                id = Long.valueOf((readValue(scanner, "Wprowadź numer id zamowienia które chcesz usunąć: ")));

                                order.deleteOrder(id);
                                break;
                            case 5:
                                order.showAllOrder();

                                id = Long.valueOf((readValue(scanner, "Wprowadź id zamówienia, które chcesz edytować:")));
                                idClients = Long.valueOf((readValue(scanner, "Wprowadź ID Kliena: ")));
                                idBooks = Long.valueOf((readValue(scanner, "Wprowadź ID Książki: ")));

                                order.updateOrder(id, idClients, idBooks);
                                break;
                            case 6:
                                System.out.println("6.Powrót do glównego menu");
                                break;
                            default:
                                System.out.println("Wprowadziłeś błędny numer ");
                        }
                        break;
                    case 4:
                        System.out.println("1.Dodaj konto \n" +
                                "2.Usuń konto \n" +
                                "3.Wyświetl konta \n" +
                                "4.Powrót do glównego menu \n" +
                                "********************************************************");
                        int menu5 = Integer.parseInt(readValue(scanner, " "));
                        switch (menu5) {
                            case 1:
                                String login = readValue(scanner, "Wprowadź login: ");
                                String password = readValue(scanner, "Wprowadź haslo: ");
                                String userName = readValue(scanner, "Wprowadź nazwę użytkownika: ");
                                String email = readValue(scanner, "Wprowadź email: ");
                                String accountType = readValue(scanner, "Wprowadź rodzaj konta: ");

                                adminAccounts.addAdminAccount(login, password, userName, email, accountType);
                                break;
                            case 2:
                                Long id = Long.valueOf((readValue(scanner, "Wprowadź numer id Administratora którego chcesz usunąć: ")));

                                if (adminAccounts.checkAccountType(id)) {
                                    adminAccounts.deleteAdminAccount(id);
                                }
                                break;
                            case 3:
                                adminAccounts.showAllAccounts();
                                break;
                            case 4:
                                System.out.println("Powrót do menu glównego");
                                break;
                            case 0:
                                System.out.println("Dziękuję za odwiedziny");
                                dbConnection.closeConnection();
                                break;
                            default:
                                System.out.println("Wprowadziłeś blędny numer");
                        }

                    case 5:
                        System.out.println("1.Podsumowanie\n" +
                                "2.Statystyka klientów \n" +
                                "3.Ilość kupionych książek  \n" +
                                "4.Powrót do glównego menu \n" +
                                "********************************************************");
                        int menu6 = Integer.parseInt(readValue(scanner, " "));
                        switch (menu6) {
                            case 1:
                                statistics.showSummary("Najcześciej kupowana książka: ", "SELECT  ksiazki.tytul, id_ksiazki, COUNT(*) AS liczba_zamowionych_ksiazek FROM zamowienia INNER JOIN ksiazki ON ksiazki.id = zamowienia.id_ksiazki GROUP BY id_ksiazki ORDER BY liczba_zamowionych_ksiazek DESC LIMIT 1");
                                statistics.showSummary("Liczba wszystkich sprzedanych książek: ", "SELECT COUNT(*) AS liczba_sprzedanych_ksiazek FROM zamowienia");
                                statistics.showSummary("Liczba sprzedanych książek w bieżącym roku: ", "SELECT COUNT(*) AS liczba_sprzedanych_ksiazek FROM zamowienia WHERE YEAR(data) = YEAR(CURRENT_DATE)");
                                statistics.showSummary("Liczba sprzedanych książek w bieżącym miesiącu: ", "SELECT COUNT(*) AS liczba_sprzedanych_ksiazek FROM zamowienia WHERE YEAR(data) = YEAR(CURRENT_DATE) AND MONTH(data) = MONTH(CURRENT_DATE)");
                                statistics.showSummary("Liczba klientów: ", "SELECT COUNT(*) AS liczba_klientów FROM klienci");
                                statistics.showSummary2("Najbardziej aktywny klient: ", "SELECT klienci.imie, klienci.nazwisko,id_klienci, COUNT(*) AS najbardziej_aktywny_klient  FROM zamowienia INNER JOIN klienci ON klienci.id = zamowienia.id_klienci GROUP BY id_klienci ORDER BY najbardziej_aktywny_klient DESC LIMIT 1");

                                break;
                            case 2:
                                statistics.showClientStatistic();
                                break;
                            case 3:
                                statistics.showOrderStatistic();
                                break;
                            case 4:
                                System.out.println("Powrót do menu glównego");
                                break;
                            case 0:
                                System.out.println("Dziękuję za odwiedziny");
                                dbConnection.closeConnection();
                                liquibaseConfiguration.updateLiquibase();
                                break;
                            default:
                                System.out.println("Wprowadziłeś blędny numer");
                        }
                }
            }
            while (menu != 0);
        } else {
            System.out.println("Błędny login lub hasło");
        }
       // liquibaseConfiguration.updateLiquibase();
    }

    public static String readValue(Scanner scanner, String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
}