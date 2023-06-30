import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        JdbConnection jdbConnection = new JdbConnection();
        Connection connection = jdbConnection.getConnection();
        Book book = new Book(connection);


        System.out.println("Program księgarnia. Wybierz menu: ");
        int menu;
        do {
            System.out.print("\n1.Książki");
            System.out.print("\n2.Klienci");
            System.out.print("\n3.Zamówienia");
            System.out.print("\n0.Zamknij program");


            Scanner scanner = new Scanner(System.in);
            menu = scanner.nextInt();

            switch (menu) {
                case 1:
                    System.out.print("\n1.Wyświetl wszystkie książki");
                    System.out.print("\n2.Wyszukaj ksiażkę");
                    System.out.print("\n3.Dodaj książkę");
                    System.out.print("\n4.Usuń książkę");
                    System.out.print("\n5.Edytuj książke");
                    System.out.print("\n6.Powrót do glównego menu");
                    System.out.print("\n********************************************************");


                    int menu2 = scanner.nextInt();
                    switch (menu2) {
                        case 1:
                            book.showAllBooks();
                            jdbConnection.closeConnection();
                            break;
                        case 2:
                            Book.showBooksByTitleOrAuthor();
                            break;
                        case 3:
                            Book.addBook();
                            break;
                        case 4:
                            book.showAllBooks();
                            Book.deleteBook();
                            break;
                        case 5:
                            book.showAllBooks();
                            Book.updateBook();
                            break;
                        case 6:
                            System.out.println("6.Powrót do glównego menu");
                            break;
                        default:
                            System.out.println("Wprowadziłeś błędny numer ");
                    }

                    break;
                case 2:
                    System.out.print("\n1.Wyświetl wszystkich klienótw");
                    System.out.print("\n2.Wyszukaj klienta");
                    System.out.print("\n3.Dodaj klienta");
                    System.out.print("\n4.Usuń klienta");
                    System.out.print("\n5.Edytuj klienta");
                    System.out.print("\n6.Powrót do glównego menu");

                    int menu3 = scanner.nextInt();
                    switch (menu3) {
                        case 1:
                            Client.showAllClients();
                            break;
                        case 2:
                            Client.showClientByNameOrSurname();
                            break;
                        case 3:
                            Client.addClient();
                            break;
                        case 4:
                            Client.deleteClient();
                            break;
                        case 5:
                            Client.updateClient();
                            break;
                        case 6:
                            System.out.println("6.Powrót do glównego menu");
                            break;
                        default:
                            System.out.println("Wprowadziłeś błędny numer ");
                            break;
                    }
                case 3:
                    System.out.print("\n1.Wyświetl wszystkie zamówienia");
                    System.out.print("\n2.Wyszukaj zamówienie");
                    System.out.print("\n3.Dodaj zamówienie");
                    System.out.print("\n4.Usuń zamówienie");
                    System.out.print("\n5.Edytuj zamówienie");
                    System.out.print("\n6.Powrót do glównego menu");

                    int menu4 = scanner.nextInt();
                    switch (menu4) {
                        case 1:
                          Order.showAllOrder();
                            break;
                        case 2:
                            System.out.println("2.Wyszukaj zamówienia");
                            break;
                        case 3:
                            Client.showAllClients();
                            book.showAllBooks();
                            Book.deleteAmount(Order.addBook());

                            break;
                        case 4:
                            System.out.println("4.Usuń zamówienia");
                            break;
                        case 5:
                            System.out.println("5.Edytuj zamówienia");
                            break;
                        case 6:
                            System.out.println("6.Powrót do glównego menu");
                            break;
                        default:
                            System.out.println("Wprowadziłeś błędny numer ");
                    }

                    break;
                case 0:
                    System.out.println("Dziękuję za odwiedziny");
                    break;
                default:
                    System.out.println("Wprowadziłeś blędny numer");
            }
        }while (menu!=0);

    }

}