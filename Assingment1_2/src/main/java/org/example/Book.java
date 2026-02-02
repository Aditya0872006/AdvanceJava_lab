package org.example;

import java.io.Serializable;
import java.time.LocalDate;

public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private int bookId;
    private String bookName;
    private String authorNames;
    private String publication;
    private LocalDate dateOfPublication;
    private float priceOfBook;
    private int totalQuantityToOrder;
    private float totalCost;

    public Book(int bookId, String bookName, String authorNames,
                String publication, LocalDate dateOfPublication,
                float priceOfBook, int totalQuantityToOrder) {

        this.bookId = bookId;
        this.bookName = bookName;
        this.authorNames = authorNames;
        this.publication = publication;
        this.dateOfPublication = dateOfPublication;
        this.priceOfBook = priceOfBook;
        this.totalQuantityToOrder = totalQuantityToOrder;
        this.totalCost = priceOfBook * totalQuantityToOrder;
    }

    // Getters
    public int getBookId() { return bookId; }
    public String getBookName() { return bookName; }
    public String getAuthorNames() { return authorNames; }
    public String getPublication() { return publication; }
    public LocalDate getDateOfPublication() { return dateOfPublication; }
    public float getPriceOfBook() { return priceOfBook; }
    public int getTotalQuantityToOrder() { return totalQuantityToOrder; }
    public float getTotalCost() { return totalCost; }
}