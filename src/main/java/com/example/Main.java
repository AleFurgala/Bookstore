package com.example;

import java.sql.Connection;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {

        JdbConnection jdbConnection = new JdbConnection();
        Connection connection = jdbConnection.getConnection();
        Book book = new Book(connection);
        Client client = new Client(connection);
        Order order = new Order(connection);
        AdminAccounts adminAccounts = new AdminAccounts(connection);


        String loginUser;
        String passwordUser;

        System.out.println("Podaj login");
        Scanner scanner1 = new Scanner(System.in);
        loginUser = scanner1.nextLine();

        String passwordFromDB = adminAccounts.getPasswordBasedLogin(loginUser);
        String decryptedPasswordFromDB = adminAccounts.dataDecryption(passwordFromDB);

        System.out.println("Podaj hasło");
        passwordUser = scanner1.nextLine();


        if (passwordUser.equals(decryptedPasswordFromDB)) {

            System.out.println("Witaj " + adminAccounts.getNameBasedLogin(loginUser));

            System.out.println("Program księgarnia. Wybierz menu: ");
            int menu;
            do {
                System.out.println("1.Książki \n2.Klienci \n3.Zamówienia \n4.Panel administratora \n0.Zamknij program ");

                Scanner scanner = new Scanner(System.in);
                menu = scanner.nextInt();

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

                        int menu2 = scanner.nextInt();
                        switch (menu2) {
                            case 1:
                                book.showAllBooks();
                                jdbConnection.closeConnection();
                                break;
                            case 2:
                                book.showAvailableBooks();
                                jdbConnection.closeConnection();
                                break;
                            case 3:
                                System.out.println("Podaj tytuł lub autora ksiązki, której szukasz");
                                Scanner scanner2 = new Scanner(System.in);
                                String titleOrAuthor = scanner2.nextLine();
                                book.showBooksByTitleOrAuthor(titleOrAuthor);
                                jdbConnection.closeConnection();
                                break;
                            case 4:
                                Scanner scanner3 = new Scanner(System.in);
                                Scanner scanner4 = new Scanner(System.in);

                                System.out.println("Wprowadź tytuł: ");
                                String title = scanner3.nextLine();
                                System.out.println("Wprowadź autora: ");
                                String author = scanner3.nextLine();
                                System.out.println("Wprowadź cenę: ");
                                int price = scanner4.nextInt();
                                System.out.println("Wprowadź ilosc: ");
                                int amount = scanner4.nextInt();
                                book.addBook(title, author, price, amount);
                                jdbConnection.closeConnection();
                                break;
                            case 5:
                                book.showAllBooks();

                                System.out.println("Wprowadź numer id książki którą chcesz usunąć: ");
                                Scanner scanner5 = new Scanner(System.in);
                                int bookId = scanner5.nextInt();
                                book.deleteBook(bookId);
                                jdbConnection.closeConnection();
                                break;
                            case 6:
                                book.showAllBooks();
                                Scanner scanner6 = new Scanner(System.in);
                                Scanner scanner7 = new Scanner(System.in);
                                System.out.println("Wprowadź id książki, którą chcesz edytować ");
                                int id = scanner6.nextInt();
                                System.out.println("Wprowadź tytuł: ");
                                title = scanner7.nextLine();
                                System.out.println("Wprowadź autora: ");
                                author = scanner7.nextLine();
                                System.out.println("Wprowadź cenę: ");
                                price = scanner7.nextInt();
                                System.out.println("Wprowadź ilosc: ");
                                amount = scanner6.nextInt();
                                book.updateBook(id, title, author, price, amount);
                                jdbConnection.closeConnection();
                                break;
                            case 7:
                                System.out.println("6.Powrót do glównego menu");
                                break;
                            default:
                                System.out.println("Wprowadziłeś błędny numer ");
                        }

                        break;
                    case 2:
                        System.out.println("\n1.Wyświetl wszystkich klienótw \n2.Wyszukaj klienta \n3.Dodaj klienta \n4.Usuń klienta \n5.Edytuj klienta \n6.Powrót do glównego menu");

                        int menu3 = scanner.nextInt();
                        switch (menu3) {
                            case 1:
                                client.showAllClients();
                                jdbConnection.closeConnection();
                                break;
                            case 2:
                                System.out.println("Podaj imię lub nazwisko: ");
                                Scanner scanner8 = new Scanner(System.in);
                                String nameOrSurname = scanner8.nextLine();
                                client.showClientByNameOrSurname(nameOrSurname);
                                jdbConnection.closeConnection();
                                break;
                            case 3:
                                Scanner scanner9 = new Scanner(System.in);
                                System.out.println("Wprowadź imie: ");
                                String name = scanner9.nextLine();
                                System.out.println("Wprowadź nazwisko: ");
                                String surname = scanner9.nextLine();
                                System.out.println("Wprowadź adres: ");
                                String address = scanner9.nextLine();
                                client.addClient(name, surname, address);
                                jdbConnection.closeConnection();
                                break;
                            case 4:
                                client.showAllClients();
                                System.out.println("Wprowadź numer id klienta, którego chcesz usunąć: ");
                                Scanner scanner10 = new Scanner(System.in);
                                int clientToDelete = scanner10.nextInt();
                                client.deleteClient(clientToDelete);
                                jdbConnection.closeConnection();
                                break;
                            case 5:
                                Scanner scanner11 = new Scanner(System.in);
                                Scanner scanner12 = new Scanner(System.in);

                                System.out.println("Wprowadź id klienta, którego chcesz edytować");
                                int id = scanner11.nextInt();
                                System.out.println("Wprowadź imie: ");
                                name = scanner12.nextLine();
                                System.out.println("Wprowadź nazwisko: ");
                                surname = scanner12.nextLine();
                                System.out.println("Wprowadź adres: ");
                                address = scanner12.nextLine();

                                client.updateClient(id, name, surname, address);
                                jdbConnection.closeConnection();
                                break;
                            case 6:
                                System.out.println("6.Powrót do glównego menu");
                                break;
                            default:
                                System.out.println("Wprowadziłeś błędny numer ");
                                break;
                        }
                    case 3:
                        System.out.println("1.Wyświetl wszystkie zamówienia \n2.Wyszukaj zamówienie \n3.Dodaj zamówienie \n4.Usuń zamówienie \n5.Edytuj zamówienie \n6.Powrót do glównego menu");

                        int menu4 = scanner.nextInt();
                        switch (menu4) {
                            case 1:
                                order.showAllOrder();
                                jdbConnection.closeConnection();
                                break;
                            case 2:
                                System.out.println("Podaj id zamowienia: ");
                                Scanner scanner13 = new Scanner(System.in);
                                int id = scanner13.nextInt();
                                order.showOrderById(id);
                                jdbConnection.closeConnection();
                                break;
                            case 3:
                                Scanner scanner14 = new Scanner(System.in);

                                client.showAllClients();
                                System.out.println("Wprowadź id  klienta: ");
                                int idClients = scanner14.nextInt();

                                book.showAvailableBooks();
                                System.out.println("Wprowadź id książki: ");
                                int idBooks = scanner14.nextInt();

                                order.addOrder(idClients, idBooks);
                                book.deleteAmount(idBooks);
                                jdbConnection.closeConnection();

                                break;
                            case 4:
                                order.showAllOrder();

                                System.out.println("Wprowadź numer id zamowienia które chcesz usunąć: ");
                                Scanner scanner15 = new Scanner(System.in);
                                id = scanner15.nextInt();

                                order.deleteOrder(id);
                                jdbConnection.closeConnection();

                                break;
                            case 5:
                                order.showAllOrder();

                                Scanner scanner16 = new Scanner(System.in);
                                System.out.println("Wprowadź id zamówienia, które chcesz edytować:");
                                id = scanner16.nextInt();
                                System.out.println("Wprowadź ID Kliena: ");
                                 idClients = scanner16.nextInt();
                                System.out.println("Wprowadź ID Książki: ");
                                idBooks = scanner16.nextInt();
                                order.updateOrder(id, idClients, idBooks);
                                jdbConnection.closeConnection();

                                break;
                            case 6:
                                System.out.println("6.Powrót do glównego menu");
                                break;
                            default:
                                System.out.println("Wprowadziłeś błędny numer ");
                        }

                        break;

                    case 4:
                        System.out.println("1.Nowe konto administratora \n2.Usuń konto 2 \n3.Opcja 3 \n4.Powrót do glównego menu");

                        int menu5 = scanner.nextInt();
                        switch (menu5) {
                            case 1:
                                adminAccounts.addAdminAccount();
                                jdbConnection.closeConnection();
                                break;
                            case 2:
                                adminAccounts.deleteAdminAccount();
                                jdbConnection.closeConnection();
                                break;
                            case 3:
                                System.out.println("opcja 3 ");
                            case 4:
                                System.out.println("Powrót do menu glównego");
                                break;
                            case 0:
                                System.out.println("Dziękuję za odwiedziny");
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

    }

}