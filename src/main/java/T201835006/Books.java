/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package T201835006;
import java.util.ArrayList;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author TOPRAK
 */
class Book{
    String name, author;
    int printing;

    public Book(String name, String author, int printing){
        this.name = name;
        this.author = author;
        this.printing = printing;
    }

    public String getName(){
        return this.name;
    }

    public String getAuthor(){
        return this.author;
    }

    public int getPrinting(){
        return this.printing;
    }
}

class Books{
    ArrayList<Book> books;

    public Books(){
        this.books = new ArrayList<Book>();
    }

    public void insert(String name, String author, int printing){
        Book book = new Book(name, author, printing);
        books.add(book);
    }

    public void write(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/t201835006","root","p73421231");

            for(Book i: books){
                String mysql = "INSERT INTO books (bookName, Author, Perinting, Available) VALUES (?, ?, ?, 1)";
                PreparedStatement pstmt = conn.prepareStatement(mysql);
                pstmt.setString(1, i.getName());
                pstmt.setString(2, i.getAuthor());
                pstmt.setInt(3,i.getPrinting());
                pstmt.executeUpdate();
            }
            if(books.isEmpty())
            {
                JOptionPane.showMessageDialog(null,"No value saved.");
            }
            else {
                JOptionPane.showMessageDialog(null,"Insertion successful. Thanks for donation.");
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        books.clear();
    }
}
