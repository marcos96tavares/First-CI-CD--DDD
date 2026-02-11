package org.example.entity;

import org.example.ojects.Available;
import org.example.ojects.BookId;

public class Book {

    private BookId bookId;
    private String title;
    private String author;
    private Available availabe;

    public Book(BookId bookId, String title, String author, Available availabe) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.availabe = availabe;
    }


    public BookId getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Available getAvailabe() {
        return availabe;
    }
}
