import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try {
            //This is used to connect the java application to the database
            //The name of the Database is : library_db
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db?useSSL=false",

                    "root",
                    "SQL2000");

            Statement st = connection.createStatement();
            ResultSet results;
            int rowsAffected;

            results = st.executeQuery("SELECT title, qty FROM books");

            while (results.next()) {
                System.out.println(results.getString("title") + ", "
                        + results.getInt("qty"));
            }


            Scanner sn = new Scanner(System.in);

            System.out.println("what would you like to do:\n" +
                    "1.Enter a new Book\n" +
                    "2.Update book information\n" +
                    "3.Delete book from Database\n" +
                    "4.Show books from database\n" +
                    "5.EXIT");

            int choice = sn.nextInt();


            switch (choice) {

                //ADDING A BOOK TO A DATABASE
                case 1:
                    System.out.println("Enter the ID of the book you would like to add");
                    int id = sn.nextInt();

                    System.out.println("What is the name of the book you would like to add?");
                    String book = sn.nextLine();

                    System.out.println();
                    System.out.println("Who is the Author of the book?");
                    String Author = sn.nextLine();
                    System.out.println("The name of the book you are adding is called " + book);

                    System.out.println("How many quantity of the book do you want?");
                    int qty = sn.nextInt();

                    String query = "insert into books values(" + id + "', '" + book + "''" + Author + "','" + qty + ")";
                    System.out.println(query);
                    rowsAffected = st.executeUpdate(query);
                    System.out.println("Query complete, " + rowsAffected + " rows added.");
                    System.out.println("\nUPDATED TABLE: ");
                    printAllFromTable(st);

                    break;


                //UPDATING DATABASE
                case 2:
                    System.out.println("Enter the Title of the book you would like to update or change details of ?");
                    String title = sn.nextLine();

                    System.out.println("What is the name of the book ?");
                    String nTitle = sn.nextLine();

                    System.out.println("Give a new id for the book, (IF NOT YOU CAN USE THE SAME ONE)");
                    int nID = sn.nextInt();

                    System.out.println("Enter the name of the Author of the book");
                    String nAuthor = sn.nextLine();

                    System.out.println("Enter the quantity of this book that's available");
                    int nQty = sn.nextInt();

                    System.out.println("Book details: \n" +
                            "ID Number: " + nID + "\n" +
                            "Title 0f the book : " + nTitle + "\n" +
                            "Author of the book :" + nAuthor + "\n" +
                            "Book quantity:" + nQty
                    );
                    //This is a varibale that contains the string data to update book database (This code is written in SQL format)
                    query = "UPDATE books set book = nTitle, author = " + nAuthor + ", qty = " + nQty + ", id = " + nID + "WHERE book = " + title + ")";

                    rowsAffected = st.executeUpdate(query);
                    System.out.println("Query complete, " + rowsAffected + " rows added.");
                    System.out.println("\nUPDATED TABLE: ");
                    printAllFromTable(st);

                    break;

                //   DELETING BOOK FROM A DATABASE
                case 3://delete books from the database

                    System.out.println("BOOKS TABLE: ");
                    printAllFromTable(st);

                    //Collecting book information
                    System.out.print("\nPlease enter the id of the book you would like to delete from the system: ");
                    id = sn.nextInt();

                    query = "DELETE FROM books WHERE id = " + id + ";";
                    System.out.println(query);
                    rowsAffected = st.executeUpdate(query);

                    System.out.println();
                    System.out.println("Query complete, " + rowsAffected + " rows added.");
                    System.out.println("\nUPDATED TABLE: ");
                    printAllFromTable(st);
                    break;

                case 4:
                    System.out.println("BOOKS TABLE: ");
                    printAllFromTable(st);
                    break;

                case 5:
                    break;
            }

            results.close();
            st.close();
            connection.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    public static void printAllFromTable(Statement st) throws
            SQLException {

        ResultSet results = st.executeQuery("SELECT id, title, author, qty FROM books");
        while (results.next()) {
            System.out.println(
                    results.getInt("id") + ", "
                            + results.getString("title") + ", "
                            + results.getString("author") + ", "
                            + results.getInt("qty")
            );
        }
    }
}
