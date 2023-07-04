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
            System.out.println("1.Książki \n2.Klienci \n3.Zamówienia \n0.Zamknij program ");

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
                            book.showBooksByTitleOrAuthor();
                            jdbConnection.closeConnection();
                            break;
                        case 3:
                            book.addBook();
                            jdbConnection.closeConnection();
                            break;
                        case 4:
                            book.showAllBooks();
                            book.deleteBook();
                            jdbConnection.closeConnection();
                            break;
                        case 5:
                            book.showAllBooks();
                            book.updateBook();
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
                    System.out.println("1.Wyświetl wszystkie zamówienia \n2.Wyszukaj zamówienie \n3.Dodaj zamówienie \n4.Usuń zamówienie \n5.Edytuj zamówienie \n6.Powrót do glównego menu");

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
        } while (menu != 0);

    }

}