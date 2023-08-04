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
                        System.out.println("1.Wyświetl wszystkie książki \n2.Wyszukaj ksiażkę \n3.Dodaj książkę \n4.Usuń książkę \n5.Edytuj książke \n6.Powrót do glównego menu \n********************************************************");

                        int menu2 = scanner.nextInt();
                        switch (menu2) {
                            case 1:
                                book.showAllBooks();
                                jdbConnection.closeConnection();
                                break;
                            case 2:
                                System.out.println("Podaj tytuł lub autora ksiązki, której szukasz");
                                Scanner scanner2 = new Scanner(System.in);
                                String titleOrAuthor = scanner2.nextLine();
                                book.showBooksByTitleOrAuthor(titleOrAuthor);
                                jdbConnection.closeConnection();
                                break;
                            case 3:
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
                                book.addBook(title,author,price,amount);
                                jdbConnection.closeConnection();
                                break;
                            case 4:
                                book.showAllBooks();

                                System.out.println("Wprowadź numer id książki którą chcesz usunąć: ");
                                Scanner scanner5 = new Scanner(System.in);
                                int bookId = scanner5.nextInt();
                                book.deleteBook(bookId);
                                jdbConnection.closeConnection();
                                break;
                            case 5:
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
                                book.updateBook(id,title,author,price,amount);
                                jdbConnection.closeConnection();
                                break;
                            case 6:
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
                                client.showClientByNameOrSurname();
                                jdbConnection.closeConnection();
                                break;
                            case 3:
                                client.addClient();
                                jdbConnection.closeConnection();
                                break;
                            case 4:
                                client.deleteClient();
                                jdbConnection.closeConnection();
                                break;
                            case 5:
                                client.updateClient();
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
                                order.showOrderById();
                                jdbConnection.closeConnection();
                                break;
                            case 3:
                                client.showAllClients();
                                book.showAllBooks();
                                book.deleteAmount(order.addOrder());
                                jdbConnection.closeConnection();

                                break;
                            case 4:
                                order.showAllOrder();
                                order.deleteOrder();
                                jdbConnection.closeConnection();

                                break;
                            case 5:
                                order.showAllOrder();
                                order.updateOrder();
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

                }}}
                while (menu != 0) ;
            } else{
                System.out.println("Błędny login lub hasło");
            }

        }

    }