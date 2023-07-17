import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        final String login = "Admin1";
        final String password = "haslo1";

        String loginUser;
        String passwordUser;

        System.out.println("Podaj login i hasło");
        Scanner scanner1 = new Scanner(System.in);
        loginUser = scanner1.nextLine();
        passwordUser = scanner1.nextLine();
        if (login.equals(loginUser) && password.equals(passwordUser)) {
            JdbConnection jdbConnection = new JdbConnection();
            Connection connection = jdbConnection.getConnection();
            Book book = new Book(connection);
            Client client = new Client(connection);
            Order order = new Order(connection);

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
                    case 0:
                        System.out.println("Dziękuję za odwiedziny");
                        break;
                    default:
                        System.out.println("Wprowadziłeś blędny numer");
                }
            } while (menu != 0);
        }else {
            System.out.println("Błędny login lub hasło");
        }

    }

}