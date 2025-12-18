package utils;

import models.Book;

import java.util.ArrayList;
import java.util.List;

public class BookInitializer {
    public static List<Book> makeBookList(){
        List<Book> books = new ArrayList<>();

        books.add(new Book("Blancanieves","Percy Jackson",15,8));
        books.add(new Book("Cenicienta","Percy Jackson",12,5));
        books.add(new Book("La bella y la bestia","Shakespeare",10,4));
        books.add(new Book("Romeo y Julieta","Shakespeare",9,3));
        books.add(new Book("Harry Potter","J. K. Rowling",8,2));

        return books;
    }
}
