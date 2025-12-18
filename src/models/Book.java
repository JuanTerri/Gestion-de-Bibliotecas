package models;

import utils.RandomHandler;

import java.util.*;

public class Book {
    private Long id;
    private String title;
    private String author;
    private Integer totalCopies;
    private Integer avalaibleCopies;


    public Book(String title, String author, Integer totalCopies, Integer avalaibleCopies) {
        this.id = RandomHandler.generateRandomLong();
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
        this.avalaibleCopies = avalaibleCopies;
    }

    public void showBook(){
        System.out.println("Id: "+ this.getId() + "\n"+ "Titulo: "+ this.getTitle() +
                "\n" + "Autor: "+ this.getAuthor() + "\n" +
                "Copias disponibles: " +this.getAvalaibleCopies()+"\n");
    }

    public static void showBooks(List<Book> books){
        for (Book book : books) book.showBook();
    }

    public static Book getBookByTitle(Scanner scanner, List<Book> bookList){
        System.out.println("Ingrese el titulo");
        String title = scanner.nextLine().trim();

        for (Book book : bookList){
            if (book.getTitle().equalsIgnoreCase(title)){
                System.out.println("El libro es:  ");
                //book.showBook();
                return book;
            }
        }
        System.out.println("No se encontro libro con ese titulo");
        return null;
    }

    public  static List<Book> getBooksByAuthor(Scanner scanner, List<Book> bookList){
        System.out.println("Ingrese el autor");
        String author = scanner.nextLine().trim();

        List<Book> foundBooks = new ArrayList<>();

        for (Book book : bookList){
            if (book.getAuthor().equalsIgnoreCase(author)) foundBooks.add(book);
        }
        if(!foundBooks.isEmpty()){ return foundBooks;}

        System.out.println("No se encontraron libros de ese autor");
        return new ArrayList<>();
    }

    public static void listBooks(List<Book> books){
        System.out.println("La lista de libros es: \n");
        for (Book book : books) book.showBook();
    }

    public void lessCopies(){
        this.avalaibleCopies = avalaibleCopies -1;
    }
    public void moreCopies(){
        this.avalaibleCopies = avalaibleCopies +1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(Integer totalCopies) {
        this.totalCopies = totalCopies;
    }

    public Integer getAvalaibleCopies() {
        return avalaibleCopies;
    }
}
