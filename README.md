### About the project

This software helps Books Store how to manage with orders.

### Versions

- 0.1
  * admin panel 
  * password encryption
  * authentication 
  * adding client, book and order
  * JUnit tests
  * liquibaseConfiguration
  * Maven
  
- 0.2
  * log table using trigger
  


### Tools

- java
- MySql Database
- Maven
- H2 Database
- triggers


### Main Menu Structure

- Books
  * show all books
  * show available books
  * search for a book
  * add book
  * delete book 
  * update book 
  * back to main menu
  

- Clients
  * show all clients 
  * search for a client
  * add client
  * delete client 
  * update client 
  * back to main menu
  

- Orders
  * show all orders 
  * search for a order
  * add order
  * delete order 
  * update order
  * back to main menu 


- Administrator panel
  * new administrator account
  * delete account
  * option 
  * back to main menu 

  
- Statistic
  * the number of books that were bought
  * clients statistics


- Close the program 

### Files in main.Book.java 

- int id - indicates a unique book number
- String title - says the name of the book
- String author - indicates who wrote the book
- int price - indicates how much the book costs
- String titleOrAuthor - says what is the title of the book or who wrote the book
- int amount - indicates the amount of the book


### Files in main.Client.java 

- int id - indicates a unique client number 
- String name - shows clients name 
- String surname - shows cliens surname 
- String adress - show clients adress 
- String nameOrSurname - shows clients name or surname 

### Files in main.Order.java

- int id - indicates a unique order number
- int idKsiazki - indicate a book that was ordered
- int idKlienci - indicate client who made an order


### Files in main.AdminAccounts.java

- String password - indicate admin password 
- String login - indicate admin login 
- String username - show admin name 

    AdminAccounts.java allows create new administrator accounts with
    fully encrypted passwords.


### Tests 

- Start creating tests using junit 5



